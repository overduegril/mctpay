package com.mctpay.wallet.service.card.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.mapper.card.CardRedeemCodeMapper;
import com.mctpay.wallet.mapper.card.MerchantCardMapper;
import com.mctpay.wallet.mapper.card.MerchantCardReceiveMapper;
import com.mctpay.wallet.mapper.point.MemberLevelRulesMapper;
import com.mctpay.wallet.mapper.system.UserMapper;
import com.mctpay.wallet.model.dto.card.AvailableCardDTO;
import com.mctpay.wallet.model.entity.card.MerchantCardEntity;
import com.mctpay.wallet.model.param.MerchantCardReceiveParam;
import com.mctpay.wallet.service.card.CardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.CARD_CANT_RECEIVE;

/**
 * @Author: guodongwei
 * @Description: 卡券业务
 * @Date: 2020/6/8 9:15
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private MerchantCardMapper merchantCardMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemberLevelRulesMapper memberLevelRulesMapper;

    @Autowired
    private MerchantCardReceiveMapper merchantCardReceiveMapper;

    @Autowired
    private CardRedeemCodeMapper cardRedeemCodeMapper;

    @Override
    public List<AvailableCardDTO> listAvailableCard(String merchantId, String userId) {
        // 查选可以领取卡券。
        List<MerchantCardEntity> merchantCardEntities = merchantCardMapper.listMerchantCard(merchantId);
        List<AvailableCardDTO> availableCardDTOs = new ArrayList<>();
        // 等级是否满足
        Integer point = userMapper.getUserPointById(userId);
        for (MerchantCardEntity merchantCardEntity : merchantCardEntities) {
            // 是否过期
            Date deadline = merchantCardEntity.getDeadline();
            int compare = DateUtil.compare(deadline, new Date());
            // 是否有库存
            Integer inventoryCount = merchantCardEntity.getInventoryCount();
            Integer requiredPoint = memberLevelRulesMapper.getPointByMemberCode(merchantCardEntity.getRequireMemberLevelCode());
            if (requiredPoint != null && point != null && compare >= 0 && inventoryCount > 0 && NumberUtil.compare(point, requiredPoint) >= 0) {
                AvailableCardDTO availableCardDTO = new AvailableCardDTO();
                BeanUtils.copyProperties(merchantCardEntity, availableCardDTO);
                availableCardDTO.setState(0);
                availableCardDTOs.add(availableCardDTO);
            }
        }
        // 修改已经领取卡券状态
        List<String> cardIds = merchantCardReceiveMapper.listReceivedCardsByUserId(userId);
        Iterator<AvailableCardDTO> iterator = availableCardDTOs.iterator();
        while (iterator.hasNext()) {
            AvailableCardDTO nextAvailableCard = iterator.next();
            for (String cardId : cardIds) {
                if (cardId.equals(nextAvailableCard.getId())) {
                    nextAvailableCard.setState(1);
                    break;
                }
            }
        }
        return availableCardDTOs;
    }

    @Override
    @Transactional
    public ResponseData receiveCard(String cardId, String userId) {
        //  获取一个可用兑换码
        String redeemCode = cardRedeemCodeMapper.getAvailableRedeemCodeByCardId(cardId);
        if (StringUtils.isEmpty(redeemCode)) {
            return new ResponseData().fail(CARD_CANT_RECEIVE.getCode(), CARD_CANT_RECEIVE.toString());
        }
        //  将随机码置为已使用
        cardRedeemCodeMapper.useRedeemCode(redeemCode);
        //  将卡券库存减少
        MerchantCardEntity merchantCardEntity = merchantCardMapper.get(cardId);
        merchantCardMapper.reduceInventoryCount(cardId, merchantCardEntity.getInventoryCount());
        //  插入领取记录
        MerchantCardReceiveParam cardReceiveParam = new MerchantCardReceiveParam();
        cardReceiveParam.setCardId(cardId);
        cardReceiveParam.setLocked(0);
        cardReceiveParam.setStatus(1);
        cardReceiveParam.setReceiveTime(new Date());
        cardReceiveParam.setCreateTime(new Date());
        cardReceiveParam.setUpdateTime(new Date());
        cardReceiveParam.setUseStateType(0);
        cardReceiveParam.setRedeemCode(redeemCode);
        cardReceiveParam.setUserId(userId);
        merchantCardReceiveMapper.insert(cardReceiveParam);
        return new ResponseData().success(null);
    }
}
