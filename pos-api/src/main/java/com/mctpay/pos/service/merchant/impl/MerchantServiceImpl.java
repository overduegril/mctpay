package com.mctpay.pos.service.merchant.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.pos.mapper.card.MerchantCardMapper;
import com.mctpay.pos.mapper.card.MerchantCardReceiveMapper;
import com.mctpay.pos.mapper.merchant.MerchantMapper;
import com.mctpay.pos.mapper.merchant.PayCheckMapper;
import com.mctpay.pos.mapper.merchant.TradeRecordMapper;
import com.mctpay.pos.mapper.merchant.WalletTradeRecordMapper;
import com.mctpay.pos.mapper.point.SummaryPointMapper;
import com.mctpay.pos.mapper.point.UseabelPointMapper;
import com.mctpay.pos.model.dto.merchant.TradeRecordDTO;
import com.mctpay.pos.model.dto.merchant.WalletTradeRecordParam;
import com.mctpay.pos.model.entity.merchant.MerchantEntity;
import com.mctpay.pos.model.entity.merchant.TradeRecordEntity;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.model.param.PayCheckParam;
import com.mctpay.pos.model.param.SweepCollectParam;
import com.mctpay.pos.model.param.TradeRecordParam;
import com.mctpay.pos.model.param.WalletTradeRecordDTO;
import com.mctpay.pos.service.merchant.MerchantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.*;

/**
 * @Author: guodongwei
 * @Description: 商户业务
 * @Date: 2020/5/23 21:02
 */
@Log4j2
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private TradeRecordMapper tradeRecordMapper;

    @Autowired
    private MerchantCardReceiveMapper merchantCardReceiveMapper;

    @Autowired
    private MerchantCardMapper merchantCardMapper;

    @Autowired
    private SummaryPointMapper summaryPointMapper;

    @Autowired
    private UseabelPointMapper useabelPointMapper;

    @Autowired
    private PayCheckMapper payCheckMapper;

    @Autowired
    private WalletTradeRecordMapper walletTradeRecordMapper;

    public String getMemberQRCode() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String memberQRCode = merchantMapper.getMemberQRCode(userEntity.getMerchantId());
        return memberQRCode;
    }

    @Override
    public ResponseData getCollectionQRCode() {
        // TODO 获取到支付需要的用户名密码
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", "Guest");
        paramMap.put("user_password", SecureUtil.md5("Guest"));
        paramMap.put("sign_string", SecureUtil.md5("Guest" + SecureUtil.md5("Guest")));
        String result = HttpUtil.get("https://ccpay.sg/dci/api_v2/get_qrcode_app", paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if ("T".equalsIgnoreCase(jsonObject.get("is_success").toString())) {
            return new ResponseData().success(jsonObject.get("qrcode_url").toString());
        }
        return new ResponseData().fail(GET_COLLECTION_QRCODE_FAIL.getCode(), GET_COLLECTION_QRCODE_FAIL.toString());
    }

    @Override
    public String getMerchantName(String merchantId) {
        MerchantEntity merchantEntity = merchantMapper.get(merchantId);
        return merchantEntity.getMerchantName();
    }

    @Override
    @Transactional
    public ResponseData sweepCollect(SweepCollectParam sweepCollectParam) throws Exception {
        // TODO 获取到支付需要的用户名密码
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", "Guest");
        paramMap.put("user_password", SecureUtil.md5("Guest"));
        String amount = sweepCollectParam.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        paramMap.put("amount", amount);
        paramMap.put("payer", sweepCollectParam.getPayer());
        // 设置订单号
        String order = UIdUtils.getUid().toString();
        paramMap.put("order", order);
        String notifyUrl = "http://39.96.29.99:/pay/merchant/sweep-collect-notify?checkStr=" + sweepCollectParam.getCheckStr();
        paramMap.put("notify_url", notifyUrl);
        paramMap.put("sign_string", SecureUtil.md5("Guest" + SecureUtil.md5("Guest") + amount + sweepCollectParam.getPayer() + order + notifyUrl));
        String result = HttpUtil.get("https://ccpay.sg/dci/api_v2/cashier_app", paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        PayCheckParam payCheckParam = new PayCheckParam();
        payCheckParam.setStatus(1);
        payCheckParam.setCreateTime(new Date());
        payCheckParam.setUpdateTime(new Date());
        payCheckParam.setCheckStr(sweepCollectParam.getCheckStr());
        if (jsonObject.get("trade_status") != null && "99".equalsIgnoreCase(jsonObject.getStr("trade_status"))) {
            postSweepSuccessPayOperate(jsonObject, sweepCollectParam);
            // 设置校验字符串
            payCheckParam.setTradeNo(jsonObject.getStr("trade_no"));
            payCheckMapper.insert(payCheckParam);
            return new ResponseData().success(null);
        }
        // 设置校验字符串
        payCheckMapper.insert(payCheckParam);
        String message = "";
        if (jsonObject.get("error_msg") != null) {
            message = jsonObject.get("error_msg").toString();
        } else if (jsonObject.get("trade_msg") != null){
            message = jsonObject.get("trade_msg").toString();
        }
        return new ResponseData().fail(SWEEP_COLLECT_FIAL.getCode(), message);
    }

    @Override
    public ResponseData refund(String order) {
        // TODO 获取到支付需要的用户名密码
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", "Guest");
        paramMap.put("user_password", SecureUtil.md5("Guest"));
        // 根据订单号获取退款金额
        String amount = tradeRecordMapper.getAmountByTradeNo(order);
        if (StringUtils.isEmpty(amount)) {
            return new ResponseData().fail(ORDER_NOT_EXIST.getCode(), ORDER_NOT_EXIST.toString());
        }
        // 根据订单号查询父订单
        String partnerTransId = tradeRecordMapper.getPartnerTransIdByTradeNo(order);
        paramMap.put("amount", amount);
        paramMap.put("order", partnerTransId);
        String notifyUrl = "http://39.96.29.99:/pay/merchant/refund-notify";
        paramMap.put("notify_url", notifyUrl);
        paramMap.put("sign_string", SecureUtil.md5("Guest" + SecureUtil.md5("Guest") + amount + partnerTransId + notifyUrl));
        String result = HttpUtil.get("https://ccpay.sg/dci/api_v2/refund_app", paramMap);
        // 改变订单支付状态，记录退款记录
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if (jsonObject.get("trade_status") != null && "99".equalsIgnoreCase(jsonObject.get("trade_status").toString())) {
            // 记录交易记录
            tradeRecordMapper.updateOrderStatus(order, 109, jsonObject.get("partner_refund_id").toString());
            return new ResponseData().success(jsonObject);
        }
        String message = "";
        if (jsonObject.get("error_msg") != null) {
            message = jsonObject.get("error_msg").toString();
        } else if (jsonObject.get("trade_msg") != null){
            message = jsonObject.get("trade_msg").toString();
        }
        return new ResponseData().fail(SWEEP_COLLECT_FIAL.getCode(), message);

    }

    @Override
    public List<TradeRecordDTO> listTradeRecord(String merchantId, String inputContent) {
        List<TradeRecordEntity> tradeRecordEntities = tradeRecordMapper.listTradeRecordByMerchantId(merchantId, inputContent);
        List<TradeRecordDTO> tradeRecordDTOs = new ArrayList<>();
        for (TradeRecordEntity tradeRecordEntity : tradeRecordEntities) {
            TradeRecordDTO tradeRecordDTO = new TradeRecordDTO();
            BeanUtils.copyProperties(tradeRecordEntity, tradeRecordDTO);
            tradeRecordDTOs.add(tradeRecordDTO);
        }
        return tradeRecordDTOs;
    }

    /**
     * 记录交易信息
     * @param jsonObject
     * @param tradeRecordParam
     */
    public static void recordTradeParam(JSONObject jsonObject, TradeRecordParam tradeRecordParam) {
        if (jsonObject.get("partner_trans_id") != null) {
            tradeRecordParam.setPartnerTransId(jsonObject.get("partner_trans_id").toString());
        }
        if (jsonObject.get("buyer_email") != null) {
            tradeRecordParam.setBuyerEmail(jsonObject.get("buyer_email").toString());
        }
        if (jsonObject.get("forex_rate") != null) {
            tradeRecordParam.setForexRate(jsonObject.get("forex_rate").toString());
        }
        if (jsonObject.get("trade_status") != null) {
            tradeRecordParam.setTradeStatus(Integer.valueOf(jsonObject.get("trade_status").toString()));
            tradeRecordParam.setOrderStatus(Integer.valueOf(jsonObject.get("trade_status").toString()));
        }
        if (jsonObject.get("total_fee") != null) {
            tradeRecordParam.setTotalFee(jsonObject.get("total_fee").toString());
        }
        if (jsonObject.get("trade_no") != null) {
            tradeRecordParam.setTradeNo(jsonObject.get("trade_no").toString());
        }
        if (jsonObject.get("merchant") != null) {
            tradeRecordParam.setMerchant(jsonObject.get("merchant").toString());
        }
        if (jsonObject.get("pay_type") != null) {
            tradeRecordParam.setPayType(jsonObject.get("pay_type").toString());
        }
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication()!= null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            tradeRecordParam.setMerchantId(userEntity.getMerchantId());
            tradeRecordParam.setOperator(userEntity.getNickname());
        }
        if (jsonObject.get("trans_amount") != null) {
            tradeRecordParam.setTransAmount(jsonObject.get("trans_amount").toString());
        }
        if (jsonObject.get("pay_time") != null) {
            tradeRecordParam.setPayTime(DateUtil.parseDateTime(jsonObject.getStr("pay_time")).toJdkDate());
        }
        tradeRecordParam.setCreateTime(new Date());
        tradeRecordParam.setUpdateTime(new Date());
        tradeRecordParam.setStatus(1);
    }

    /**
     * @Description 扫码支付后操作
     * @Date 9:54 2020/6/15
     **/
    public void postSweepSuccessPayOperate(JSONObject jsonObject, SweepCollectParam sweepCollectParam) throws Exception {
        // 记录交易记录
        TradeRecordParam tradeRecordParam = new TradeRecordParam();
        recordTradeParam(jsonObject, tradeRecordParam);
        tradeRecordMapper.insert(tradeRecordParam);
        // 核销优惠券
        if (!StringUtils.isEmpty(sweepCollectParam.getCardId())) {
            // 校验卡券是否被领取
            String redeemCode = merchantCardReceiveMapper.getRedeemCodeByCardIdAndUserId(sweepCollectParam.getCardId(), sweepCollectParam.getUserId());
            if (StringUtils.isEmpty(redeemCode)) {
                throw new Exception("输入卡券不存在或已经使用");
            }
            // 领取记录修改
            merchantCardReceiveMapper.updateUseStateByRedeenCode(1, jsonObject.getStr("trade_no")  ,redeemCode);
            // 卡券库存修改
            merchantCardMapper.decInventory(sweepCollectParam.getCardId());
        }
        // 添加积分以及积分记录
        String integerAmount = new BigDecimal(tradeRecordParam.getTransAmount()).setScale(0, BigDecimal.ROUND_FLOOR).toString();
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 积分变动详情
        WalletTradeRecordParam walletTradeRecordParam = new WalletTradeRecordParam();
        walletTradeRecordParam.setStatus(1);
        walletTradeRecordParam.setCreateTime(new Date());
        walletTradeRecordParam.setChangePoint(integerAmount);
        walletTradeRecordParam.setUpdateTime(new Date());
        walletTradeRecordParam.setTransAmount(jsonObject.getStr("trans_amount"));
        walletTradeRecordMapper.insert(walletTradeRecordParam);
        summaryPointMapper.incPoint(userEntity.getMerchantId(), Integer.valueOf(integerAmount));
        useabelPointMapper.incPoint(userEntity.getMerchantId(), Integer.valueOf(integerAmount));
    }

    @Override
    public void insertTradeRecord(TradeRecordParam tradeRecordParam) {
        tradeRecordMapper.insert(tradeRecordParam);
        // 添加积分以及积分记录
        BigDecimal transAmount = new BigDecimal(tradeRecordParam.getTransAmount());
        String integerAmount = transAmount.setScale(0, BigDecimal.ROUND_FLOOR).toString();
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 积分变动详情
        WalletTradeRecordParam walletTradeRecordParam = new WalletTradeRecordParam();
        walletTradeRecordParam.setStatus(1);
        walletTradeRecordParam.setCreateTime(new Date());
        walletTradeRecordParam.setChangePoint(integerAmount);
        walletTradeRecordParam.setUpdateTime(new Date());
        walletTradeRecordParam.setTransAmount(tradeRecordParam.getTransAmount());
        walletTradeRecordMapper.insert(walletTradeRecordParam);
        summaryPointMapper.incPoint(userEntity.getMerchantId(), Integer.valueOf(integerAmount));
        useabelPointMapper.incPoint(userEntity.getMerchantId(), Integer.valueOf(integerAmount));
    }

    @Override
    public Integer countByTradeNo(String tradeNo) {
        Integer rows = tradeRecordMapper.countByTradeNo(tradeNo);
        return rows;
    }

    public void updateOrderStatus(String orderNo, Integer status, String partnerTransId) {
        tradeRecordMapper.updateOrderStatus(orderNo, status, partnerTransId);
    }

    @Override
    public TradeRecordDTO getPayResult(String checkStr) {
        TradeRecordEntity tradeRecordEntity = payCheckMapper.getBycheckStr(checkStr);
        TradeRecordDTO tradeRecordDTO = new TradeRecordDTO();
        BeanUtils.copyProperties(tradeRecordEntity, tradeRecordDTO);
        return tradeRecordDTO;
    }

    @Override
    public void updatePayCheck(PayCheckParam payCheckParam) {
        payCheckMapper.updateByCheckStr(payCheckParam);
    }

}
