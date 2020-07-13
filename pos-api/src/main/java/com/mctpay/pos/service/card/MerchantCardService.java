package com.mctpay.pos.service.card;

import com.mctpay.pos.model.dto.card.CardDTO;
import com.mctpay.pos.model.dto.card.CardUseHistoryDTO;

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

    /**
     * 获取商户的卡券使用记录
     * @param merchantId
     * @return
     */
    List<CardUseHistoryDTO> listCardUseHistory(String merchantId, String cardId);

}
