package com.mctpay.pos.service.card.impl;

import com.mctpay.pos.mapper.card.MerchantCardMapper;
import com.mctpay.pos.mapper.card.MerchantCardReceiveMapper;
import com.mctpay.pos.model.dto.card.CardDTO;
import com.mctpay.pos.model.dto.card.CardUseHistoryDTO;
import com.mctpay.pos.model.entity.card.CardUseHistoryEntity;
import com.mctpay.pos.model.entity.card.MerchantCardEntity;
import com.mctpay.pos.service.card.MerchantCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantCardServiceImpl implements MerchantCardService {
    @Autowired
    private MerchantCardMapper merchantCardMapper;

    @Autowired
    private MerchantCardReceiveMapper merchantCardReceiveMapper;

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

    @Override
    public List<CardDTO> listAvailableCards(String userId, BigDecimal amount) {
        List<MerchantCardEntity> merchantCardEntities = merchantCardMapper.listReceivedCardsByUserId(userId, amount);
        List<CardDTO> cardDTOs = new ArrayList<>();
        for (MerchantCardEntity merchantCardEntity : merchantCardEntities) {
            CardDTO cardDTO = new CardDTO();
            BeanUtils.copyProperties(merchantCardEntity, cardDTO);
            cardDTOs.add(cardDTO);
        }
        return cardDTOs;
    }

    @Override
    public List<CardUseHistoryDTO> listCardUseHistory(String merchantId, String cardId) {
        List<CardUseHistoryEntity> cardUseHistoryEntities = merchantCardReceiveMapper.listCardUseHistory(merchantId, cardId);
        List<CardUseHistoryDTO> cardUseHistoryDTOs = new ArrayList<>();
        for (CardUseHistoryEntity cardUseHistoryEntity : cardUseHistoryEntities) {
            CardUseHistoryDTO cardUseHistoryDTO = new CardUseHistoryDTO();
            BeanUtils.copyProperties(cardUseHistoryEntity, cardUseHistoryDTO);
            cardUseHistoryDTOs.add(cardUseHistoryDTO);
        }
        return cardUseHistoryDTOs;
    }


}
