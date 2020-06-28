package com.mctpay.pos.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.dto.card.CardUseHistoryDTO;
import com.mctpay.pos.model.entity.card.CardUseHistoryEntity;
import com.mctpay.pos.model.entity.card.MerchantCardReceiveEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-06-05 10:04:21
 */
@Repository
public interface MerchantCardReceiveMapper extends BaseMapper<MerchantCardReceiveEntity> {

    /**
     * 根据卡券id以及用户id获取兑换码
     * @param cardId
     * @param userId
     * @return
     */
    String getRedeemCodeByCardIdAndUserId(@Param("cardId") String cardId, @Param("userId") String userId);

    /**
     * 更新已经领取优惠券的使用状态
     * @param state
     * @param redeemCode
     */
    void updateUseStateByRedeenCode(@Param("state") Integer state, @Param("tradeNo") String tradeNo, @Param("redeemCode") String redeemCode);

    /**
     * 获取卡券使用历史
     * @param merchantId
     * @return
     */
    List<CardUseHistoryEntity> listCardUseHistory(String merchantId);
}
