package com.mctpay.manager.service.merchant.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.mapper.merchant.MerchantMapper;
import com.mctpay.manager.model.dto.merchant.MerchantDtO;
import com.mctpay.manager.model.entity.merchant.MerchantEntity;
import com.mctpay.manager.model.param.MerchantParam;
import com.mctpay.manager.model.param.UpdateMerchantParam;
import com.mctpay.manager.service.merchant.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 商户serviceImpl
 * @Date: 2020/2/24 10:27
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;
    @Override
    public ResponseData insertMerchant(MerchantParam merchantParam) {
        merchantMapper.insertMerchant(merchantParam);
        return new ResponseData().success(null);
    }

    @Override
    public List<MerchantDtO> listMerchantByInput(String inputContent) {
        List<MerchantEntity> merchantEntities = merchantMapper.listMerchantByInput(inputContent);
        List<MerchantDtO> merchantDtOS = new ArrayList<>();
        for(MerchantEntity merchantEntity : merchantEntities){
            MerchantDtO merchantDtO = new MerchantDtO();
            BeanUtils.copyProperties(merchantEntity,merchantDtO);
            merchantDtOS.add(merchantDtO);
        }
        return merchantDtOS;
    }

    @Override
    public ResponseData switchMerchant(Long merchantId, Integer state) {
        merchantMapper.updateSwitchMerchant(merchantId,state);
        return new ResponseData().success(null);
    }

    @Override
    public ResponseData updateMerchant(UpdateMerchantParam updateMerchantParam) {
        merchantMapper.updateMerchant(updateMerchantParam);
        return new ResponseData().success(null);
    }
}
