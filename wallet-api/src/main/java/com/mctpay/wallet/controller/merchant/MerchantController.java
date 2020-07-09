package com.mctpay.wallet.controller.merchant;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.wallet.model.dto.merchant.MerchantDtO;
import com.mctpay.wallet.model.dto.merchant.TradeRecordDTO;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.model.param.DynamicCollectionQRCodeParam;
import com.mctpay.wallet.model.param.PayCheckParam;
import com.mctpay.wallet.model.param.SweepCollectNotifyParam;
import com.mctpay.wallet.model.param.TradeRecordParam;
import com.mctpay.wallet.service.merchant.MerchantService;
import com.mctpay.wallet.service.merchant.impl.MerchantServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
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
 * @Date: 2020/2/27 17:10
 */
@Api(value = "商户相关", tags = "商户")
@RestController
@RequestMapping("manager-merchant")
@Log4j
public class MerchantController {


    @Autowired
    private MerchantService merchantService;


    @ApiOperation(value = "分页查询商户", notes = "分页查询商户 ;",  httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/listMerchanttByInput")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "维度", paramType = "query"),
            @ApiImplicitParam(name = "lon", value = "经度", paramType = "query")
    })
    public ResponseData<List<MerchantDtO>> listMerchanttByInput( @RequestParam(required = false) Double lat,  @RequestParam(required = false) Double lon,  @RequestParam(required = false) String inputContent, @RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<MerchantDtO> merchantDtOs = merchantService.listMerchantByInput(lat,lon,inputContent);
        return new ResponsePageInfo<List<MerchantDtO>>().success(merchantDtOs, pageInfo);
    }

    @ApiOperation(value = "交易历史记录", notes = "交易历史记录")
    @PostMapping("/insertTradeRecord")
    public ResponseData<Object> insertTradeRecord(@RequestBody TradeRecordParam tradeRecordParam) {
        tradeRecordParam.setCreateTime(new Date());
        tradeRecordParam.setUpdateTime(new Date());
        tradeRecordParam.setStatus(1);
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        tradeRecordParam.setUserId(userEntity.getId());
        merchantService.insertTradeRecord(tradeRecordParam);
        return new ResponseData<>().success(null);
    }

    @ApiOperation(value = "获取交易订单列表", notes = "获取交易订单列表,orderStatus：99 表示交易成功，109表示退款成功")
    @PostMapping("listTradeRecord")
    public ResponsePageInfo<List<TradeRecordDTO>> listTradeRecordByInput(String inputContent, @RequestBody PageParam pageParam) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<TradeRecordDTO> tradeRecordDTOS = merchantService.listTradeRecord(userEntity.getId(), inputContent);
        return new ResponsePageInfo<List<TradeRecordDTO>>().success(tradeRecordDTOS, pageInfo);
    }

    @ApiOperation(value = "获取商户动态收款二维码", notes = "获取商户动态收款二维码", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/dynamic-collection-qrcode")
    public ResponseData<String> getDynamicCollectionQRCode(@RequestBody @Validated DynamicCollectionQRCodeParam dynamicCollectionQRCodeParam) {
        ResponseData collectionQRCode = merchantService.getDynamicCollectionQRCode(dynamicCollectionQRCodeParam);
        return collectionQRCode;
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
}
