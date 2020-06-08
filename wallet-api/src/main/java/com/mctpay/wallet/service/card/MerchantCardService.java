package com.mctpay.wallet.service.card;


import com.mctpay.wallet.model.dto.card.CardDTO;

import java.util.List;

public interface MerchantCardService {

    /**
     * @Description 修改商户集合
     * @Date 23:39 2020/5/11
     **/
    List<CardDTO> listCard(String merchantId);

}
