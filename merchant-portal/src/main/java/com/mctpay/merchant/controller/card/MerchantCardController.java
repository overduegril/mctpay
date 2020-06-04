package com.mctpay.merchant.controller.card;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.merchant.model.dto.card.CardDTO;
import com.mctpay.merchant.model.dto.card.CardTypeDTO;
import com.mctpay.merchant.model.dto.card.ReduceTypeDTO;
import com.mctpay.merchant.model.dto.template.ReserveTemplateDTO;
import com.mctpay.merchant.model.entity.system.UserEntity;
import com.mctpay.merchant.model.enums.CardTypeEnum;
import com.mctpay.merchant.model.enums.ReduceTypeEnum;
import com.mctpay.merchant.model.param.MerchantCardParam;
import com.mctpay.merchant.service.card.MerchantCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        merchantCardParam.setStatus(1);
        merchantCardParam.setCreateTime(new Date());
        merchantCardParam.setUpdateTime(new Date());
        return merchantCardService.insertMerchantCard(merchantCardParam);
    }

    @ApiOperation(value = "商家卡券集合", notes = "商家卡券集合", httpMethod = "POST")
    @PostMapping("/listCards")
    public ResponseData<List<CardDTO>> listCards(@RequestBody(required = false) PageParam pageParam, String inputContent){
        if (pageParam != null) {
            Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
            if (!StringUtils.isEmpty(pageParam.getOrder())) {
                PageHelper.orderBy(pageParam.getOrder());
            }
            List<CardDTO> cardDTOs = merchantCardService.listCard(inputContent);
            return new ResponsePageInfo<List<CardDTO>>().success(cardDTOs, pageInfo);
        }else {
            List<CardDTO> cardDTOs = merchantCardService.listCard(inputContent);
            return new ResponseData<List<CardDTO>>().success(cardDTOs);
        }
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
        return responseData;
    }

    /**
     * @Description 删除商家卡券
     * @Date 14:39 2020/4/27
     **/
    @ApiOperation(value = "删除商家卡券", notes = "删除商家卡券", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/deleteCard")
    public ResponseData deleteMerchantCard(@RequestParam Long id){
        merchantCardService.deleteMerchantCard(id);
        return new ResponseData().success(null);
    }

    @ApiOperation(value = "获取卡券类型", notes = "获取卡券类型", httpMethod = "GET")
    @GetMapping("/listCardType")
    public ResponseData<List<CardTypeDTO>> listCardType() {
        List<CardTypeDTO> cardTypeDTOs = new ArrayList<>();
        for (CardTypeEnum cardTypeEnum : CardTypeEnum.values()) {
            CardTypeDTO cardTypeDTO = new CardTypeDTO();
            cardTypeDTO.setTypeCode(cardTypeEnum.toString());
            cardTypeDTO.setTypeName(cardTypeEnum.getName());
            cardTypeDTOs.add(cardTypeDTO);
        }
        return new ResponseData<List<CardTypeDTO>>().success(cardTypeDTOs);
    }

    @ApiOperation(value = "获取卡券优惠类型", notes = "获取卡券优惠类型", httpMethod = "GET")
    @GetMapping("/listReduceType")
    public ResponseData<List<ReduceTypeDTO>> listReduceType() {
        List<ReduceTypeDTO> reduceTypeDTOs = new ArrayList<>();
        for (ReduceTypeEnum reduceTypeEnum : ReduceTypeEnum.values()) {
            ReduceTypeDTO reduceTypeDTO = new ReduceTypeDTO();
            reduceTypeDTO.setReduceType(reduceTypeEnum.toString());
            reduceTypeDTO.setReduceTypeName(reduceTypeEnum.getName());
            reduceTypeDTOs.add(reduceTypeDTO);
        }
        return new ResponseData<List<ReduceTypeDTO>>().success(reduceTypeDTOs);
    }
}
