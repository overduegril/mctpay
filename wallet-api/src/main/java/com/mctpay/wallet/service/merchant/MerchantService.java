package com.mctpay.wallet.service.merchant;

import com.mctpay.wallet.model.dto.merchant.MerchantDtO;
import com.mctpay.wallet.model.dto.merchant.TradeRecordDTO;
import com.mctpay.wallet.model.param.TradeRecordParam;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 商户service
 * @Date: 2020/2/24 10:28
 */
public interface MerchantService {


    /**
     * @Description 商户集合
     * @param  lat 维度
     * @param lon 精度
     * @Date 23:39 2020/2/28
     **/
    List<MerchantDtO> listMerchantByInput(Double lat, Double lon, String inputContent);

    /**
     * 插入交易记录
     * @param tradeRecordParam
     */
    void insertTradeRecord(TradeRecordParam tradeRecordParam);

    /**
     * 获取交易记录
     * @param userId
     * @return
     */
    List<TradeRecordDTO> listTradeRecord(String userId, String inputContent);
}
