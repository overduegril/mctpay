package com.mctpay.pos.controller.merchant;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.model.param.SweepCollectNotifyParam;
import com.mctpay.pos.model.param.SweepCollectParam;
import com.mctpay.pos.service.merchant.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "扫码收款回调接口", notes = "扫码收款回调接口,trade_status：99 表示成功，90表示未知", httpMethod = "POST", consumes = "application/json")
    public String sweepCollectNotify(@RequestBody SweepCollectNotifyParam sweepCollectNotifyParam) {
        log.debug("-----------------------------------------");
        log.debug("=========================================");
        log.debug(sweepCollectNotifyParam);
        return "SUCCESS";
    }

}
