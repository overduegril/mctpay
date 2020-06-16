package com.mctpay.manager.service.merchant.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.OSSUtils;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.config.OSSProperties;
import com.mctpay.manager.mapper.merchant.MerchantMapper;
import com.mctpay.manager.mapper.merchant.MerchantUserMapper;
import com.mctpay.manager.mapper.pos.PosUserMapper;
import com.mctpay.manager.mapper.system.RoleMapper;
import com.mctpay.manager.mapper.system.UserRoleMapper;
import com.mctpay.manager.model.dto.merchant.MerchantDtO;
import com.mctpay.manager.model.entity.merchant.MerchantEntity;
import com.mctpay.manager.model.entity.system.RoleEntity;
import com.mctpay.manager.model.param.*;
import com.mctpay.manager.service.merchant.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private PosUserMapper posUserMapper;

    private final static String roleName ="ROLE_SHOP_OWNER";
    private final static String description ="管理平台创建的商户角色";

    @Override
    @Transactional
    public ResponseData insertMerchant(MerchantParam merchantParam) throws IOException {
        merchantMapper.insertMerchant(merchantParam);
        // 创建店长的权限
//        RoleParam roleParam = new RoleParam();
//        roleParam.setRoleName(roleName);
//        roleParam.setDescription(description);
//        roleParam.setCreateTime(new Date());
//        roleParam.setUpdateTime(new Date());
//        roleParam.setStatus(1);
//        roleMapper.insertRole(roleParam);
        RoleEntity role = roleMapper.getByRoleName(roleName);
        // 用户于角色进行关联
        UserRoleParam userRoleParam = new UserRoleParam();
        userRoleParam.setRoleId(role.getId());
        userRoleParam.setUserId(Long.valueOf(merchantParam.getId()));
        userRoleParam.setStatus(1);
        userRoleParam.setCreateTime(new Date());
        userRoleParam.setUpdateTime(new Date());
        userRoleMapper.insert(userRoleParam);
        // 将商户信息添加到账户体系中
        MerchantUserParam merchantUserParam = new MerchantUserParam();
        merchantUserParam.setId(UIdUtils.getUid().toString());
        merchantUserParam.setMerchantId(merchantParam.getId());
        merchantUserParam.setNickname(merchantParam.getLegalPerson());
        merchantUserParam.setEmail(merchantParam.getEmail());
        merchantUserParam.setPhoneNumber(merchantParam.getPhoneNumber());
        merchantUserParam.setRemark(merchantParam.getCreator());
        merchantUserParam.setPassword(SecureUtils.MD5Encrypt("123456"));
        merchantUserParam.setStatus(2);
        merchantUserParam.setCreateTime(new Date());
        merchantUserParam.setUpdateTime(new Date());

        // 添加用户二维码
        File tempFile = File.createTempFile(merchantParam.getId(), ".jpg");
        QrCodeUtil.generate(merchantParam.getId().toString(), 300, 300, tempFile);
        InputStream inputStream = new FileInputStream(tempFile);
        String userQurcodeUrl = OSSUtils.uploadFileInputStream(ossProperties.getBucketName(), ossProperties.getQrcodePath() + tempFile.getName(), inputStream);
        merchantParam.setMemberQrcodeUrl(userQurcodeUrl);
        merchantUserMapper.insertUser(merchantUserParam);
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
                    } else if (!inRange && businessStatus != -1) {
                        businessStatus = 0;
                    }
                }
            }
            merchantEntity.setBusinessStatus(businessStatus);
        }
        List<MerchantDtO> merchantDtOs = new ArrayList<>();
        for (MerchantEntity merchantEntity : merchantEntities) {
            MerchantDtO merchantDtO = new MerchantDtO();
            BeanUtils.copyProperties(merchantEntity, merchantDtO);
            merchantDtOs.add(merchantDtO);
        }
        return merchantDtOs;
    }

    @Override
    @Transactional
    public ResponseData switchMerchant(String merchantId, Integer state) {
        merchantMapper.updateSwitchMerchant(merchantId, state);
        return new ResponseData().success(null);
    }

    @Override
    @Transactional
    public ResponseData updateMerchant(MerchantParam merchantParam) {
        merchantMapper.updateMerchant(merchantParam);
        // 如果更新的是法人。同时修改商户昵称
        if (!StringUtils.isEmpty(merchantParam.getLegalPerson())) {
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
