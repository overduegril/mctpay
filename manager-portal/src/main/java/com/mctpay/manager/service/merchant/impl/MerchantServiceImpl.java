package com.mctpay.manager.service.merchant.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.druid.sql.visitor.functions.If;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.OSSUtils;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.manager.config.OSSProperties;
import com.mctpay.manager.mapper.merchant.MerchantMapper;
import com.mctpay.manager.mapper.merchant.MerchantUserMapper;
import com.mctpay.manager.model.dto.merchant.MerchantDtO;
import com.mctpay.manager.model.entity.merchant.MerchantEntity;
import com.mctpay.manager.model.entity.system.UserEntity;
import com.mctpay.manager.model.param.MerchantParam;
import com.mctpay.manager.model.param.MerchantUserParam;
import com.mctpay.manager.service.merchant.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.mctpay.common.constants.ErrorCode.EMAIL_HAS_BEEN_USED;
import static com.mctpay.common.constants.ErrorCode.PHONENUM_HAS_BEEN_USED;
import static com.mctpay.common.constants.ErrorCode.USERNAME_HAS_BEEN_USED;

/**
 * @Author: guodongwei
 * @Description: 商户serviceImpl
 * @Date: 2020/2/24 10:27
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private OSSProperties oSSProperties;

    @Autowired
    private MerchantUserMapper merchantUserMapper;

    @Override
    @Transactional
    public ResponseData insertMerchant(MerchantParam merchantParam) {
        // 设置商户二维码
        String path = "/qrcode" + merchantParam.getId() + ".jpg";
        QrCodeUtil.generate(merchantParam.getId().toString(), 300,300, FileUtil.file(path));
        String ossPath = OSSUtils.uploadFile(oSSProperties.getBucketName(), path, oSSProperties.getQrcodePath() + path);
        System.out.println(ossPath);
        merchantParam.setMemberQrcodeUrl(ossPath);
        new File(path).delete();
        merchantMapper.insertMerchant(merchantParam);
        // 设置账户信息
        // 验证手机号，邮箱是否重复
        Integer emailCount = merchantUserMapper.countEmail(merchantParam.getEmail());
        if (emailCount != 0) {
            return new ResponseData<>().fail(EMAIL_HAS_BEEN_USED.getCode(), EMAIL_HAS_BEEN_USED.getMessage());
        }
        Integer phoneNumberCount = merchantUserMapper.countPhoneNumber(merchantParam.getPhoneNumber());
        if (phoneNumberCount != 0) {
            return new ResponseData<>().fail(PHONENUM_HAS_BEEN_USED.getCode(), PHONENUM_HAS_BEEN_USED.getMessage());
        }
        // 获取商户用户参数
        MerchantUserParam merchantUserParam = new MerchantUserParam();
        BeanUtils.copyProperties(merchantParam, merchantUserParam);
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        merchantUserParam.setRemark("operator:" + userEntity.getUsername());
        merchantUserParam.setNickname(merchantParam.getLegalPerson());
        // 设置默认密码为123456
        merchantUserParam.setPassword(SecureUtils.MD5Encrypt("123456"));
        merchantUserMapper.insertUser(merchantUserParam);
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
    public ResponseData switchMerchant(Long merchantId, Integer state) {
        merchantMapper.updateSwitchMerchant(merchantId,state);
        // 同时冻结商户的的登陆账户
        merchantUserMapper.updateSwitchUser(merchantId, state);
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
    public void resetPassword(Long merchantId) {
        String password = SecureUtils.MD5Encrypt("123456");
        merchantUserMapper.updatePassword(password, merchantId);
    }

    /**
     * @Description 保存营业执照
     * @Date 14:16 2020/3/4
     **/
    @Override
    public void insertBusinessLicense(String businessLicenseUrl, Long merchantId) {
        merchantMapper.insertBusinessLicense(businessLicenseUrl, merchantId);
    }
}
