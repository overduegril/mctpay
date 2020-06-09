package com.mctpay.pos.job;

import com.mctpay.pos.mapper.merchant.TradeRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: guodongwei
 * @Description: 同步订单
 * @Date: 2020/6/7 14:08
 */
public class SyncTradeJob {

    @Autowired
    private TradeRecordMapper tradeRecordMapper;

    public void run() {
    }

}
