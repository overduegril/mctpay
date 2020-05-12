package com.mctpay.merchant.controller.card;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.merchant.model.dto.card.CardDTO;
import com.mctpay.merchant.model.dto.template.ReserveTemplateDTO;
import com.mctpay.merchant.model.entity.system.UserEntity;
import com.mctpay.merchant.model.param.MerchantCardParam;
import com.mctpay.merchant.service.card.MerchantCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: chenshubiao
 * @Description: 商家卡券
 * @Date: 2020/05/06 11:46
 */
@Api(value = "商家卡券相关", tags = "商家卡券")
@RestController
@RequestMapping("card")
public class MerchantCardController {
    @Autowired
    private MerchantCardService merchantCardService;

    @ApiOperation(value = "添加商家卡券", notes = "添加商家卡券",  httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertCard")
    public ResponseData insertMerchantCard(@RequestBody MerchantCardParam merchantCardParam){
        String id = UIdUtils.getUid().toString();
        merchantCardParam.setId(id);
        merchantCardParam.setStatus(2);
        merchantCardParam.setCreateTime(new Date());
        merchantCardParam.setUpdateTime(new Date());
        return merchantCardService.insertMerchantCard(merchantCardParam);
    }

    @ApiOperation(value = "商家卡券集合", notes = "商家卡券集合", httpMethod = "POST")
    @PostMapping("/listCards")
    public ResponseData listCards(@RequestBody PageParam pageParam){
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<CardDTO> cardDTOs = merchantCardService.listCard();
        return new ResponsePageInfo<List<CardDTO>>().success(cardDTOs, pageInfo);
    }

    /**
     * @Description 修改商家卡券
     * @Date 14:39 2020/4/27
     **/
    @ApiOperation(value = "修改商家卡券", notes = "修改商家卡券", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/updateCard")
    public ResponseData updateMerchantCard(@RequestBody MerchantCardParam merchantCardParam){
        merchantCardParam.setUpdateTime(new Date());
        ResponseData responseData = merchantCardService.updateMerchantCard(merchantCardParam);
        return new ResponseData().success(null);
    }

}
