package com.mctpay.pos.service.card.impl;

import com.mctpay.pos.mapper.card.MerchantCardMapper;
import com.mctpay.pos.model.dto.card.CardDTO;
import com.mctpay.pos.model.entity.card.MerchantCardEntity;
import com.mctpay.pos.service.card.MerchantCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantCardServiceImpl implements MerchantCardService {
    @Autowired
    private MerchantCardMapper merchantCardMapper;


    @Override
    public List<CardDTO> listCard(String  merchanId) {
        List<MerchantCardEntity> merchantCardEntities = merchantCardMapper.listCardByMerchanId(merchanId);
        List<CardDTO> cardDTOs = new ArrayList<>();
        for(MerchantCardEntity merchantCardEntity : merchantCardEntities){
            CardDTO cardDTO = new CardDTO();
            BeanUtils.copyProperties(merchantCardEntity, cardDTO);
            cardDTOs.add(cardDTO);
        }
        return cardDTOs;
    }


}
