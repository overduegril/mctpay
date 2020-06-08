package com.mctpay.pos.service.card;

import com.mctpay.pos.model.dto.card.CardDTO;

import java.util.List;

public interface MerchantCardService {

    /**
     * @Description 修改商户集合
     * @Date 23:39 2020/5/11
     **/
    List<CardDTO> listCard(String merchantId);

}
