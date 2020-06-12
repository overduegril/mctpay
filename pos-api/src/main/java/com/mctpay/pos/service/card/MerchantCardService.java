package com.mctpay.pos.service.card;

import com.mctpay.pos.model.dto.card.CardDTO;

import java.math.BigDecimal;
import java.util.List;

public interface MerchantCardService {

    /**
     * @Description 修改商户集合
     * @Date 23:39 2020/5/11
     **/
    List<CardDTO> listCard(String merchantId);

    /**
     * 获取可用于支付的卡券
     * @param userId
     * @param amount
     * @return
     */
    List<CardDTO> listAvailableCards(String userId, BigDecimal amount);

}
