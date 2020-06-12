package com.mctpay.wallet.service.point.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.mapper.point.GiftMapper;
import com.mctpay.wallet.model.dto.point.GiftDTO;
import com.mctpay.wallet.model.entity.point.GiftEntity;
import com.mctpay.wallet.service.point.GiftService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 积分
 * @Date: 2020/2/24 10:27
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftMapper giftMapper;

    @Override
    public List<GiftDTO> listGiftByInput() {
        List<GiftEntity> giftEntities = giftMapper.listGiftByInput();
        List<GiftDTO> giftDTOs = new ArrayList<>();
        giftEntities.forEach(giftEntity -> {
            GiftDTO giftDTO = new GiftDTO();
            BeanUtils.copyProperties(giftEntity,giftDTO);
            giftDTOs.add(giftDTO);
        });
        return giftDTOs;
    }
    /**
     * @Description  冻结激活积分商品
     * @Date 16:38  2020/2/27
     **/
    @Override
    public ResponseData switchGift(String giftId, Integer state) {
        giftMapper.updateSwitchGift(giftId, state);
        return new ResponseData().success(null);
    }
}
