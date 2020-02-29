package com.mctpay.manager.service.point.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.mapper.point.GiftMapper;
import com.mctpay.manager.model.dto.point.GiftDTO;
import com.mctpay.manager.model.entity.point.GiftEntity;
import com.mctpay.manager.model.param.GiftParam;
import com.mctpay.manager.service.point.GiftService;
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
    /**
     * @Description 插入积分商品
     * @Date 16:45 2020/2/26
     **/
    @Override
    public ResponseData insertGift(GiftParam giftParam) {
        giftMapper.insertGift(giftParam);
        return new ResponseData().success(null);
    }

    @Override
    public List<GiftDTO> listGiftByInput(String inputContent) {
        List<GiftEntity> giftEntities = giftMapper.listGiftByInput(inputContent);
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
    public ResponseData switchGift(Long giftId, Integer state) {
        giftMapper.updateSwitchGift(giftId, state);
        return new ResponseData().success(null);
    }
}
