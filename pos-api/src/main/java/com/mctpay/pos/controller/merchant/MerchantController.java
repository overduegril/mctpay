package com.mctpay.pos.controller.merchant;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.uitl.EmailUtils;
import com.mctpay.pos.model.dto.merchant.TradeRecordDTO;
import com.mctpay.pos.model.dto.merchant.TradeSummaryDTO;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.model.param.*;
import com.mctpay.pos.service.merchant.MerchantService;
import com.mctpay.pos.service.merchant.impl.MerchantServiceImpl;
import io.micrometer.core.instrument.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 商户控制层
 * @Date: 2020/5/23 20:59
 */
@Api(value = "商户管理", tags = "商户管理")
@RestController
@RequestMapping("merchant")
@Log4j2
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @ApiOperation(value = "获取商户二维码", notes = "获取商户二维码", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/member-qrcode")
    public ResponseData<String> getMemberQRCode() {
        String memberQRCode = merchantService.getMemberQRCode();
        return new ResponseData<String>().success(memberQRCode);
    }

    @ApiOperation(value = "获取商户静态收款二维码", notes = "获取商户静态收款二维码", httpMethod = "GET", consumes = "application/json")
    @GetMapping("/static-collection-qrcode")
    public ResponseData<String> getStaticCollectionQRCode() {
        ResponseData collectionQRCode = merchantService.getStaticCollectionQRCode();
        return collectionQRCode;
    }

    @ApiOperation(value = "获取商户动态收款二维码", notes = "获取商户动态收款二维码", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/dynamic-collection-qrcode")
    public ResponseData<String> getDynamicCollectionQRCode(@RequestBody @Validated DynamicCollectionQRCodeParam dynamicCollectionQRCodeParam) {
        ResponseData collectionQRCode = merchantService.getDynamicCollectionQRCode(dynamicCollectionQRCodeParam);
        return collectionQRCode;
    }

    @ApiOperation(value = "获取商户名称", notes = "获取商户名称（用以收款时使用）", httpMethod = "GET", consumes = "application/json")
    @GetMapping("/getMerchantName")
    public ResponseData<String> getMerchantName() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String merchantName = merchantService.getMerchantName(userEntity.getMerchantId());
        return new ResponseData<String>().success(merchantName);
    }

    @ApiOperation(value = "扫码收款", notes = "扫码收款,trade_status：99 表示成功，90表示未知", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/sweep-collect")
    public ResponseData sweepCollect(@RequestBody SweepCollectParam sweepCollectParam) throws Exception {
        ResponseData responseData = merchantService.sweepCollect(sweepCollectParam);
        return responseData;
    }

    @ApiOperation(value = "退款", notes = "退款", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/refund")
    public ResponseData refund(@RequestParam("order") String order) {
        ResponseData responseData = merchantService.refund(order);
        return responseData;
    }

    @ApiOperation(value = "扫码收款回调接口", notes = "扫码收款回调接口,trade_status：99 表示成功，90表示未知")
    @PostMapping("sweep-collect-notify")
    public String sweepCollectNotify(SweepCollectNotifyParam sweepCollectNotifyParam, @RequestParam String checkStr) {
        log.debug(sweepCollectNotifyParam);
        // 判断是否该单已经完成
        if ("T".equalsIgnoreCase(sweepCollectNotifyParam.getIs_success()) && sweepCollectNotifyParam.getTrade_no() != null) {
            log.debug("-----------------------------------------");
            Integer rows = merchantService.countByTradeNo(sweepCollectNotifyParam.getTrade_no());
            if (rows == 0) {
                TradeRecordParam tradeRecordParam = new TradeRecordParam();
                MerchantServiceImpl.recordTradeParam(JSONUtil.parseObj(sweepCollectNotifyParam), tradeRecordParam);
                log.debug(tradeRecordParam);
                // 插入记录
                tradeRecordParam.setOrderStatus(99);
                merchantService.insertTradeRecord(tradeRecordParam);
                // 校验码交易号更新
                PayCheckParam payCheckParam = new PayCheckParam();
                payCheckParam.setUpdateTime(new Date());
                log.debug(checkStr);
                payCheckParam.setCheckStr(checkStr);
                payCheckParam.setTradeNo(sweepCollectNotifyParam.getTrade_no());
                merchantService.updatePayCheck(payCheckParam);
            }
            return "SUCCESS";
        }
        log.debug("=========================================");
        return "FAIL";
    }

    @ApiOperation(value = "动态二维码收款回到接口", notes = "动态二维码收款回到接口")
    @PostMapping("dynamic-qrcode-collect-notify")
    public String dynamicQrcodeCollectNotify(SweepCollectNotifyParam sweepCollectNotifyParam, @RequestParam String checkStr) {
        log.debug(sweepCollectNotifyParam);
        // 判断是否该单已经完成
        if ("T".equalsIgnoreCase(sweepCollectNotifyParam.getIs_success()) && sweepCollectNotifyParam.getTrade_no() != null) {
            log.debug("-----------------------------------------");
            Integer rows = merchantService.countByTradeNo(sweepCollectNotifyParam.getTrade_no());
            if (rows == 0) {
                TradeRecordParam tradeRecordParam = new TradeRecordParam();
                MerchantServiceImpl.recordTradeParam(JSONUtil.parseObj(sweepCollectNotifyParam), tradeRecordParam);
                log.debug(tradeRecordParam);
                // 插入记录
                tradeRecordParam.setOrderStatus(99);
                tradeRecordParam.setTradeStatus(99);
                merchantService.insertTradeRecord(tradeRecordParam);
                // 校验码交易号更新
                PayCheckParam payCheckParam = new PayCheckParam();
                payCheckParam.setUpdateTime(new Date());
                payCheckParam.setCheckStr(checkStr);
                payCheckParam.setTradeNo(sweepCollectNotifyParam.getTrade_no());
                merchantService.updatePayCheck(payCheckParam);
            }
            return "SUCCESS";
        }
        log.debug("=========================================");
        return "FAIL";
    }

    @ApiOperation(value = "扫码退款回调接口", notes = "扫码退款回调接口")
    @PostMapping("refund-notify")
    public String refundNotify(SweepCollectNotifyParam sweepCollectNotifyParam) {
        log.debug("-----------------------------------------");
        log.debug(sweepCollectNotifyParam);
        // 判断是否该单已经完成
        if ("T".equalsIgnoreCase(sweepCollectNotifyParam.getIs_success()) && sweepCollectNotifyParam.getTrade_no() != null) {
            TradeRecordParam tradeRecordParam = new TradeRecordParam();
            MerchantServiceImpl.recordTradeParam(JSONUtil.parseObj(sweepCollectNotifyParam), tradeRecordParam);
            log.debug(tradeRecordParam);
            // 更新记录为已退款
            merchantService.updateOrderStatus(tradeRecordParam.getTradeNo() , 109, tradeRecordParam.getPartnerTransId());
        }
        log.debug("=========================================");
        return "SUCCESS";
    }

    @ApiOperation(value = "获取交易订单列表", notes = "获取交易订单列表,orderStatus：99 表示交易成功，109表示退款成功")
    @PostMapping("listTradeRecord")
    public ResponsePageInfo<List<TradeRecordDTO>> listTradeRecordByInput(String inputContent, @RequestBody PageParam pageParam) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<TradeRecordDTO> tradeRecordDTOS = merchantService.listTradeRecord(userEntity.getMerchantId(), inputContent);
        return new ResponsePageInfo<List<TradeRecordDTO>>().success(tradeRecordDTOS, pageInfo);
    }

    @ApiOperation(value = "未直接获取到支付结果时，轮询接口，获取支付状态及信息", notes = "如果没有直接获取到支付结果时，轮训接口，获取支付状态及信息", httpMethod = "GET")
    @GetMapping("/pay-result")
    public ResponseData<TradeRecordDTO> getPayResult(@RequestParam("checkStr") String checkStr) {
        TradeRecordDTO payResult = merchantService.getPayResult(checkStr);
        return new ResponseData<TradeRecordDTO>().success(payResult);
    }

    @ApiOperation(value = "今日收款和笔数", notes = "今日收款和笔数")
    @PostMapping("today-trade-summary")
    public ResponseData<TradeSummaryDTO> getDayTradeSummary() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date startDate = DateUtil.beginOfDay(new Date());
        TradeSummaryDTO tradeSummaryDTO = merchantService.getDayTradeSummary(userEntity.getMerchantId(), startDate, new Date(), null);
        return new ResponseData<TradeSummaryDTO>().success(tradeSummaryDTO);
    }

    @ApiOperation(value = "收款统计", notes = "今日收款和笔数")
    @PostMapping("trade-summary")
    public ResponseData<TradeSummaryDTO> getTradeSummary(@RequestBody @Validated TradeSummaryParam tradeSummaryParam) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date startDate, endDate;
        String operatorId = null;
        if ("1".equals(tradeSummaryParam.getType())) {
            startDate = DateUtil.beginOfDay(new Date());
            endDate = new Date();
            operatorId = userEntity.getId();
        } else {
            startDate = tradeSummaryParam.getStart();
            endDate = tradeSummaryParam.getEnd();

        }
        TradeSummaryDTO tradeSummary = merchantService.getDayTradeSummary(userEntity.getMerchantId(), startDate, endDate, operatorId);
        return new ResponseData<TradeSummaryDTO>().success(tradeSummary);
    }

    @ApiOperation(value = "发送注册邮件", notes = "发送注册邮件", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/send-regist-email")
    public ResponseData sendRegistEmail(@RequestBody @Validated SendRegistEmailParam sendRegistEmailParam) throws Exception {
        String subject = "商户注册申请";
        String content = "我要注册。";
        EmailUtils.sendRegistEmail(sendRegistEmailParam.getEmail(), subject, content);
        return new ResponseData().success(null);
    }

    @ApiOperation(value = "发送反馈邮件", notes = "发送反馈邮件", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/send-feedback-email")
    public ResponseData sendFeedbackEmail(@RequestBody @Validated SendFeedbackEmailParam sendFeedbackEmailParam) throws Exception {
        String subject = "反馈";
        EmailUtils.sendRegistEmail(sendFeedbackEmailParam.getEmail(), subject, sendFeedbackEmailParam.getContent());
        return new ResponseData().success(null);
    }
}
