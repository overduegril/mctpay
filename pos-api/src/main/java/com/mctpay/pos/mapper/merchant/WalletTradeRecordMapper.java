package com.mctpay.pos.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.param.WalletTradeRecordParam;
import com.mctpay.pos.model.entity.merchant.WalletTradeRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-06-07 14:37:14
 */
@Repository
public interface WalletTradeRecordMapper extends BaseMapper<WalletTradeRecordEntity> {

    /**
     * 插入交易记录
     * @param walletTradeRecordParam
     */
    void insert(WalletTradeRecordParam walletTradeRecordParam);

}
