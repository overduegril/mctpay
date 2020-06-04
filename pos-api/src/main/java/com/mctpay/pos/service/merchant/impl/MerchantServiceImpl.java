package com.mctpay.pos.service.merchant.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.pos.mapper.merchant.MerchantMapper;
import com.mctpay.pos.mapper.merchant.TradeRecordMapper;
import com.mctpay.pos.model.entity.merchant.MerchantEntity;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.model.param.SweepCollectParam;
import com.mctpay.pos.model.param.TradeRecordParam;
import com.mctpay.pos.service.merchant.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import static com.mctpay.common.constants.ErrorCode.GET_COLLECTION_QRCODE_FAIL;
import static com.mctpay.common.constants.ErrorCode.SWEEP_COLLECT_FIAL;

/**
 * @Author: guodongwei
 * @Description: 商户业务
 * @Date: 2020/5/23 21:02
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private TradeRecordMapper tradeRecordMapper;

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
        MerchantEntity merchantEntity = merchantMapper.get(Long.valueOf(merchantId));
        return merchantEntity.getMerchantName();
    }

    @Override
    public ResponseData sweepCollect(SweepCollectParam sweepCollectParam) {
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
        String notifyUrl = "http://39.96.29.99:9104/merchant/sweep-collect-notify";
        paramMap.put("notify_url", notifyUrl);
        paramMap.put("sign_string", SecureUtil.md5("Guest" + SecureUtil.md5("Guest") + amount + sweepCollectParam.getPayer() + order + notifyUrl));
        String result = HttpUtil.get("https://ccpay.sg/dci/api_v2/cashier_app", paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        if ("99".equalsIgnoreCase(jsonObject.get("trade_status").toString())) {
            // 记录交易记录
            TradeRecordParam tradeRecordParam = new TradeRecordParam();
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
            UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            tradeRecordParam.setMerchantId(userEntity.getMerchantId());
            if (jsonObject.get("trans_amount") != null) {
                tradeRecordParam.setTransAmount(jsonObject.get("trans_amount").toString());
            }
            if (jsonObject.get("pay_time") != null) {

                tradeRecordParam.setPayTime(DateUtil.parseDateTime(jsonObject.get("pay_time").toString()).toJdkDate());
            }
            tradeRecordParam.setOperator(userEntity.getId());
            tradeRecordMapper.insert(tradeRecordParam);
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

}
