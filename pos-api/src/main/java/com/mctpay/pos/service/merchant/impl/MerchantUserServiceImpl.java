package com.mctpay.pos.service.merchant.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.pos.enums.EmailCodeEnum;
import com.mctpay.pos.mapper.merchant.MerchantUserMapper;
import com.mctpay.pos.mapper.system.EmailCodeMapper;
import com.mctpay.pos.mapper.system.SmsCodeMapper;
import com.mctpay.pos.model.entity.merchant.MerchantUserEntity;
import com.mctpay.pos.model.entity.system.EmailCodeEntity;
import com.mctpay.pos.model.entity.system.SmsCodeEntity;
import com.mctpay.pos.model.enums.PhoneCodeEnum;
import com.mctpay.pos.service.merchant.MerchantUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.mctpay.common.constants.ErrorCode.*;

@Service
public class MerchantUserServiceImpl implements MerchantUserService {

    @Resource
    private MerchantUserMapper merchantUserMapper;
    @Resource
    private EmailCodeMapper emailCodeMapper;

    private SmsCodeMapper smsCodeMapper;

    @Override
    public ResponseData updatefindBackPassword(Integer type, String number, String password, String code) {

        MerchantUserEntity merchantUserEntity = null;
        if(type == 0){
            EmailCodeEntity emailCodeByEmail = emailCodeMapper.getEmailCodeByEmail(number, EmailCodeEnum.FINDBACKPASSWORD.getEmailCodeType());
            if (!code.equals(emailCodeByEmail.getCode())) {
                return new ResponseData().fail(EMAILCODE_NOT_CORRECT.getCode(), EMAILCODE_NOT_CORRECT.getMessage());
            }
            if (emailCodeByEmail.getExpirationTime().compareTo(new Date()) < 0) {
                return new ResponseData().fail(EMAILCODE_HAS_EXPIRED.getCode(), EMAILCODE_HAS_EXPIRED.getMessage());
            }
            merchantUserEntity = merchantUserMapper.findMerchantUserByEmail(number);
        }else if(type == 1){
            SmsCodeEntity phoneAndBussinessType = smsCodeMapper.getByPhoneAndBussinessType(number, PhoneCodeEnum.FINDBACKPASSWORD.getPhoneCodeType());
            if (!code.equals(phoneAndBussinessType.getCode())) {
                return new ResponseData().fail(SMSCODE_NOT_CORRECT.getCode(), SMSCODE_NOT_CORRECT.getMessage());
            }
            if (phoneAndBussinessType.getExpirationTime().compareTo(new Date()) < 0) {
                return new ResponseData().fail(SMSCODE_HAS_EXPIRED.getCode(), SMSCODE_HAS_EXPIRED.getMessage());
            }
            merchantUserEntity = merchantUserMapper.findMerchantUserByPhone(number);
        }


        if(merchantUserEntity == null){
            return new ResponseData().fail(USER_NOT_REG.getCode(),USER_NOT_REG.getMessage());
        }

        merchantUserMapper.updatePassword(SecureUtils.MD5Encrypt(password), merchantUserEntity.getId().toString());

        return new ResponseData().success(null);
    }
}
