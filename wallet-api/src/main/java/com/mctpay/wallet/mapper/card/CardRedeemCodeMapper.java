package com.mctpay.wallet.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.card.CardRedeemCodeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-06-05 10:04:21
 */
@Repository
public interface CardRedeemCodeMapper extends BaseMapper<CardRedeemCodeEntity> {

    /**
     * 获取一个可用兑换码
     * @param cardId
     * @return
     */
    String getAvailableRedeemCodeByCardId(String cardId);

    /**
     * @Description 设置兑换码为已经使用
     * @Date 21:13 2020/6/8
     **/
    void useRedeemCode(String redeemCode);
}
