package com.mctpay.pos.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.merchant.PayCheckEntity;
import com.mctpay.pos.model.entity.merchant.TradeRecordEntity;
import com.mctpay.pos.model.param.PayCheckParam;
import org.springframework.stereotype.Repository;

/**
 * @author dongwei_guo
 * @date 2020-06-15 14:11:39
 */
@Repository
public interface PayCheckMapper extends BaseMapper<PayCheckEntity> {

    /**
     * 插入支付校验信息
     *
     * @param payCheckParam
     */
    void insert(PayCheckParam payCheckParam);

    /**
     * 更新校验信息
     *
     * @param payCheckParam
     */
    void updateByCheckStr(PayCheckParam payCheckParam);

    /**
     * 根据校验字符串获取校验信息
     * @param checkStr
     */
    TradeRecordEntity getBycheckStr(String checkStr);

}
