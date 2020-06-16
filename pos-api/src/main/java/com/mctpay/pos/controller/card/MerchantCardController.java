package com.mctpay.pos.controller.card;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.pos.model.dto.card.CardDTO;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.service.card.MerchantCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @ApiOperation(value = "商家卡券集合", notes = "商家卡券集合", httpMethod = "POST")
    @PostMapping("/listCards")
    public ResponseData<List<CardDTO>> listCards(@RequestBody(required = false) PageParam pageParam, String inputContent){
        if (pageParam != null) {
            Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
            if (!StringUtils.isEmpty(pageParam.getOrder())) {
                PageHelper.orderBy(pageParam.getOrder());
            }
            UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<CardDTO> cardDTOs = merchantCardService.listCard(userEntity.getMerchantId());
            return new ResponsePageInfo<List<CardDTO>>().success(cardDTOs, pageInfo);
        }else {
            List<CardDTO> cardDTOs = merchantCardService.listCard(inputContent);
            return new ResponseData<List<CardDTO>>().success(cardDTOs);
        }
    }

    @ApiOperation(value = "获取可以用于支付的卡券", notes = "获取可以用于支付的卡券", httpMethod = "GET")
    @GetMapping("/available-cards")
    public ResponseData<List<CardDTO>> listAvailableCards(@RequestParam String userId, BigDecimal amount) {
        List<CardDTO> cardDTOs = merchantCardService.listAvailableCards(userId, amount);
        return new ResponseData<List<CardDTO>>().success(cardDTOs);
    }

}
