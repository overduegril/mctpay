package com.mctpay.merchant.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.merchant.model.param.CardRedeemCodeParam;
import com.mctpay.merchant.model.entity.card.CardRedeemCodeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-06-05 10:04:21
 */
@Repository
public interface CardRedeemCodeMapper extends BaseMapper<CardRedeemCodeEntity> {

    /**
     * 批量创建兑换吗
     */
    void insertBatch(@Param("cardRedeemCodeParams") List<CardRedeemCodeParam> cardRedeemCodeParams);

}
