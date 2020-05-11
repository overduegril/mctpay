package com.mctpay.merchant.controller.card;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.merchant.model.entity.system.UserEntity;
import com.mctpay.merchant.model.param.MerchantCardParam;
import com.mctpay.merchant.service.card.MerchantCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: chenshubiao
 * @Description: 商家卡券
 * @Date: 2020/05/06 11:46
 */
@Api(value = "商家卡券相关", tags = "商家")
@RestController
@RequestMapping("merchant-portal")
public class MerchantCardController {
    @Autowired
    private MerchantCardService merchantCardService;

    @ApiOperation(value = "添加商家卡券", notes = "添加商家卡券",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertMerchantCard")
    public ResponseData insertMerchantCard(@RequestBody MerchantCardParam merchantCardParam){
        String id = UIdUtils.getUid().toString();
        merchantCardParam.setId(id);
        merchantCardParam.setStatus(2);
        merchantCardParam.setCreateTime(new Date());
        merchantCardParam.setUpdateTime(new Date());
        return merchantCardService.insertMerchantCard(merchantCardParam);
    }


}
