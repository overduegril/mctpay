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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Transactional
    public ResponseData switchMerchant(String merchantId, Integer state) {
        merchantMapper.updateSwitchMerchant(merchantId,state);
        return new ResponseData().success(null);
    }
    @Override
    @Transactional
    public ResponseData updateMerchant(MerchantParam merchantParam) {
        merchantMapper.updateMerchant(merchantParam);
        // 如果更新的是法人。同时修改商户昵称
        if (! StringUtils.isEmpty(merchantParam.getLegalPerson())){
            merchantUserMapper.updateUserNickName(merchantParam.getLegalPerson(), merchantParam.getId());
        }
        return new ResponseData().success(null);
    }

    /**
     * @Description 重置密码
     * @Date 23:23 2020/3/3
     **/
    @Override
    public void resetPassword(String merchantId) {
        String password = SecureUtils.MD5Encrypt("123456");
        merchantUserMapper.updatePassword(password, merchantId);
    }

    /**
     * @Description 保存营业执照
     * @Date 14:16 2020/3/4
     **/
    @Override
    public void insertBusinessLicense(String businessLicenseUrl, String merchantId) {
        merchantMapper.insertBusinessLicense(businessLicenseUrl, merchantId);
    }
}
