package com.mctpay.manager.service.merchant.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.manager.mapper.merchant.MerchantMapper;
import com.mctpay.manager.mapper.merchant.MerchantUserMapper;
import com.mctpay.manager.model.dto.merchant.MerchantDtO;
import com.mctpay.manager.model.entity.merchant.MerchantEntity;
import com.mctpay.manager.model.param.MerchantParam;
import com.mctpay.manager.service.merchant.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 商户serviceImpl
 * @Date: 2020/2/24 10:27
 */
@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private MerchantUserMapper merchantUserMapper;

    @Override
    public ResponseData insertMerchant(MerchantParam merchantParam) {
        merchantMapper.insertMerchant(merchantParam);
        return new ResponseData().success(null);
    }

    @Override
    public List<MerchantDtO> listMerchantByInput(String inputContent) {
        List<MerchantEntity> merchantEntities = merchantMapper.listMerchantByInput(inputContent);
        // 计算商户的营业时间
        for (MerchantEntity merchantEntity : merchantEntities) {
            Integer businessStatus = 1;
            String businessTime = merchantEntity.getBusinessTime();
            if (!StringUtils.isEmpty(businessTime)) {
                String[] businessTimes = businessTime.split(";");
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                for (String time : businessTimes) {
                    boolean inRange = false;
                    try {
                        String startTime = time.substring(0, time.indexOf("-"));
                        String endTime = time.substring(time.indexOf("-") + 1);
                        Date startDate = DateUtil.parseDateTime(date + " " + startTime + ":00");
                        Date endDate = DateUtil.parseDateTime(date + " " + endTime + ":00");
                        inRange = DateUtil.isIn(new Date(), startDate, endDate);
                        // 防止输错影响到整个列表的查询
                    } catch (Exception e) {
                        log.debug(e.toString());
                        businessStatus = -1;
                    }
                    if (inRange && businessStatus != -1) {
                        businessStatus = 1;
                        break;
                    }else if (!inRange && businessStatus != -1){
                        businessStatus = 0;
                    }
                }
            }
                merchantEntity.setBusinessStatus(businessStatus);
        }
        List<MerchantDtO> merchantDtOs = new ArrayList<>();
        for(MerchantEntity merchantEntity : merchantEntities){
            MerchantDtO merchantDtO = new MerchantDtO();
            BeanUtils.copyProperties(merchantEntity,merchantDtO);
            merchantDtOs.add(merchantDtO);
        }
        return merchantDtOs;
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
