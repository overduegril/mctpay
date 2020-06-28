package com.mctpay.wallet.service.merchant.impl;

import com.mctpay.wallet.mapper.merchant.TradeRecordMapper;
import com.mctpay.wallet.model.dto.merchant.TradeRecordDTO;
import com.mctpay.wallet.model.entity.merchant.MctTradeRecordEntity;
import com.mctpay.wallet.service.merchant.MctTradeRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenshubiao
 * @Description: 交易记录serviceImpl
 * @Date: 2020/6/18  14:08
 */
@Service
public class MctTradeRecordServiceImpl  implements MctTradeRecordService {

    @Autowired
    private  TradeRecordMapper tradeRecordMapper;

    /**
     * @Description 获取交易记录
     * @Date 18:12 2020/6/18
     **/
    @Override
    public List<TradeRecordDTO> listTradeRecord(String userId,String input) {
        List<MctTradeRecordEntity> tradeRecordEntities = tradeRecordMapper.listTradeRecordByMerchantId(userId,input);
        List<TradeRecordDTO> tradeRecordDTOs = new ArrayList<>();
        for(MctTradeRecordEntity tradeRecordEntitie : tradeRecordEntities){
            TradeRecordDTO  tradeRecordDTO = new TradeRecordDTO();
            BeanUtils.copyProperties(tradeRecordEntitie,tradeRecordDTO);
            tradeRecordDTOs.add(tradeRecordDTO);
        }
        return tradeRecordDTOs;
    }
}
