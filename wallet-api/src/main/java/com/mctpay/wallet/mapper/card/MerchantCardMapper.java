package com.mctpay.wallet.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.card.MerchantCardEntity;
import com.mctpay.wallet.model.param.MerchantCardParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface MerchantCardMapper extends BaseMapper<MerchantCardEntity> {
    /**
     * @Description 添加商户卡券
     * @Date 23:50 2020/5/6
     **/
    List<MerchantCardEntity> listMerchantCard(String merchantId);

    /**
     * @Description 卡券集合
     * @Date 23:50 2020/5/11
     **/
    List<MerchantCardEntity> listCardByInput(@Param("inputContent") String inputContent);

    /**
     * 减少库存数量
     * @param cardId
     */
    void reduceInventoryCount(@Param("cardId")String cardId, @Param("inventoryCount") Integer inventoryCount);

}
