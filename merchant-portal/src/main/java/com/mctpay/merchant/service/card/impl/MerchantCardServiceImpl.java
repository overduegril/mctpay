package com.mctpay.merchant.service.card.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.mapper.card.CardRedeemCodeMapper;
import com.mctpay.merchant.mapper.card.MerchantCardMapper;
import com.mctpay.merchant.model.dto.card.CardDTO;
import com.mctpay.merchant.model.param.CardRedeemCodeParam;
import com.mctpay.merchant.model.entity.card.MerchantCardEntity;
import com.mctpay.merchant.model.param.MerchantCardParam;
import com.mctpay.merchant.service.card.MerchantCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantCardServiceImpl implements MerchantCardService {
    @Autowired
    private MerchantCardMapper merchantCardMapper;

    @Autowired
    private CardRedeemCodeMapper cardRedeemCodeMapper;
    /**
     * @Description 插入商户兑换码
     * @Date 16:03 2020/5/11
     **/
    @Override
    public ResponseData insertMerchantCard(MerchantCardParam merchantCardParam) {
        merchantCardMapper.insertMerchantCard(merchantCardParam);
        return new ResponseData().success(null);
    }

    @Override
    public List<CardDTO> listCard(String inputContent) {
        List<MerchantCardEntity> merchantCardEntities = merchantCardMapper.listCardByInput(inputContent);
        List<CardDTO> cardDTOs = new ArrayList<>();
        for(MerchantCardEntity merchantCardEntity : merchantCardEntities){
            CardDTO cardDTO = new CardDTO();
            BeanUtils.copyProperties(merchantCardEntity, cardDTO);
            cardDTOs.add(cardDTO);
        }
        return cardDTOs;
    }

    @Override
    public ResponseData updateMerchantCard(MerchantCardParam merchantCardParam) {
        merchantCardMapper.updateMerchantCard(merchantCardParam);
        return new ResponseData().success(null);
    }

    @Override
    public void deleteMerchantCard(Long id) {
        merchantCardMapper.delete(id);
    }

    @Override
    public void insertBatchRedeemCode(List<CardRedeemCodeParam> cardRedeemCodeParams) {
        cardRedeemCodeMapper.insertBatch(cardRedeemCodeParams);
    }
}
