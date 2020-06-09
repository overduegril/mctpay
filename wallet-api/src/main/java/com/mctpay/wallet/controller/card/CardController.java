package com.mctpay.wallet.controller.card;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.wallet.model.dto.card.AvailableCardDTO;
import com.mctpay.wallet.model.dto.merchant.MerchantDtO;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.service.card.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 卡券控制层
 * @Date: 2020/6/8 9:05
 */
@Api(value = "卡券", tags = "卡券")
@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiOperation(value = "获取可领取的卡券", notes = "获取可领取的卡券", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/available-cards")
    public ResponseData<List<AvailableCardDTO>> listAvailableCard(@RequestParam String merchantId) {
//        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
//        if (!StringUtils.isEmpty(pageParam.getOrder())) {
//            PageHelper.orderBy(pageParam.getOrder());
//        }
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<AvailableCardDTO> availableCardDTOs = cardService.listAvailableCard(merchantId, userEntity.getId());
        return new ResponseData<List<AvailableCardDTO>>().success(availableCardDTOs);
    }

    @ApiOperation(value = "领取优惠券", notes = "领取优惠券", httpMethod = "POST")
    @PostMapping("/receive-card")
    public ResponseData receiveCard(@RequestParam String cardId) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseData responseData = cardService.receiveCard(cardId, userEntity.getId());
        return responseData;
    }
}