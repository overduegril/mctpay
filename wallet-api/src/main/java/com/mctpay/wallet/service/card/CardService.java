package com.mctpay.wallet.service.card;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.model.dto.card.AvailableCardDTO;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 卡券业务接口
 * @Date: 2020/6/8 9:14
 */
public interface CardService {

    /**
     * 获取可以领取的优惠券
     * @param merchantId
     */
    List<AvailableCardDTO> listAvailableCard(String merchantId, String userId);

    /**
     * @Description 领取
     * @Date 20:37 2020/6/8
     **/
    ResponseData receiveCard(String cardId, String userId);
}
