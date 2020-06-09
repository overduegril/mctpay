package com.mctpay.pos.controller.merchant;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.pos.model.dto.merchant.TradeRecordDTO;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.model.param.SweepCollectNotifyParam;
import com.mctpay.pos.model.param.SweepCollectParam;
import com.mctpay.pos.model.param.TradeRecordParam;
import com.mctpay.pos.service.merchant.MerchantService;
import com.mctpay.pos.service.merchant.impl.MerchantServiceImpl;
import io.micrometer.core.instrument.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/collection-qrcode")
    public ResponseData<String> getCollectionQRCode() {
        ResponseData collectionQRCode = merchantService.getCollectionQRCode();
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
    public ResponseData sweepCollect(@RequestBody SweepCollectParam sweepCollectParam) {
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
    public String sweepCollectNotify(SweepCollectNotifyParam sweepCollectNotifyParam) {
        log.debug("-----------------------------------------");
        log.debug(sweepCollectNotifyParam);
        // 判断是否该单已经完成
        if ("T".equalsIgnoreCase(sweepCollectNotifyParam.getIs_success()) && sweepCollectNotifyParam.getTrade_no() != null) {
            Integer rows = merchantService.countByTradeNo(sweepCollectNotifyParam.getTrade_no());
            if (rows == 0) {
                TradeRecordParam tradeRecordParam = new TradeRecordParam();
                MerchantServiceImpl.recordTradeParam(JSONUtil.parseObj(sweepCollectNotifyParam), tradeRecordParam);
                log.debug(tradeRecordParam);
                // 插入记录
                if (tradeRecordParam.getTradeStatus() == null) {
                    tradeRecordParam.setTradeStatus(99);
                }
                merchantService.insertTradeRecord(tradeRecordParam);
            }
        }
        log.debug("=========================================");
        return "SUCCESS";
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

}
