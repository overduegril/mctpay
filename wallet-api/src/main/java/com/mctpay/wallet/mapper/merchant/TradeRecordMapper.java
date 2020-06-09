package com.mctpay.wallet.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.merchant.MctTradeRecordEntity;
import com.mctpay.wallet.model.entity.merchant.TradeRecordEntity;
import com.mctpay.wallet.model.param.TradeRecordParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-06-07 14:37:14
 */
@Repository
public interface TradeRecordMapper extends BaseMapper<TradeRecordEntity> {

    /**
     * 插入交易记录
     * @param tradeRecordParam
     */
    void insert(TradeRecordParam tradeRecordParam);

    /**
     * 获取交易记录
     * @param userId
     * @return
     */
    List<MctTradeRecordEntity> listTradeRecordByMerchantId(@Param("userId") String userId, @Param("inputContent") String inputContent);
}
