package com.mctpay.pos.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.card.MerchantCardEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface MerchantCardMapper extends BaseMapper<MerchantCardEntity> {

    /**
     * @Description 卡券集合
     * @Date 23:50 2020/5/11
     **/
    List<MerchantCardEntity> listCardByMerchanId(@Param("merchanId") String merchanId);

    /**
     * 根据用户名获取已领取卡券ID集合
     *
     * @param userId
     * @return
     */
    List<MerchantCardEntity> listReceivedCardsByUserId(@Param("userId") String userId, @Param("amount") BigDecimal amount);

    /**
     * 库存减1
     * @param cardId
     */
    void decInventory(@Param("cardId") String cardId);
}
