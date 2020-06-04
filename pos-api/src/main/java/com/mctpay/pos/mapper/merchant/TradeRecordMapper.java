package com.mctpay.pos.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.merchant.TradeRecordEntity;
import com.mctpay.pos.model.param.TradeRecordParam;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-06-03 15:49:54
 */
@Repository
public interface TradeRecordMapper extends BaseMapper<TradeRecordEntity> {

    /**
     * 插入交易记录
     * @param tradeRecordParam
     */
    void insert(TradeRecordParam tradeRecordParam);

}
