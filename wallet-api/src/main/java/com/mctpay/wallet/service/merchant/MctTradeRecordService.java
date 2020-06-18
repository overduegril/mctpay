package com.mctpay.wallet.service.merchant;

import com.mctpay.wallet.model.dto.merchant.TradeRecordDTO;

import java.util.List;

/**
 * @Author: chenshubiao
 * @Description: 交易记录service
 * @Date: 2020/6/18  14:08
 */
public interface MctTradeRecordService {

    /**
     * @Description 商户集合
     * @Date 18:12 2020/6/18
     **/
    List<TradeRecordDTO> listTradeRecord(String userId,String input);
}
