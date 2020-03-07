package com.mctpay.wallet.service.system.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.mapper.system.EmailCodeMapper;
import com.mctpay.wallet.model.entity.system.EmailCodeEntity;
import com.mctpay.wallet.model.param.EmailCodeParam;
import com.mctpay.wallet.service.system.EmailCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.mctpay.common.constants.ErrorCode.EMAILCODE_HAS_EXPIRED;
import static com.mctpay.common.constants.ErrorCode.EMAILCODE_NOT_CORRECT;

/**
 * @Author: guodongwei
 * @Description: 邮箱验证码服务
 * @Date: 2020/3/2 16:52
 */
@Service
public class EmailCodeServiceImpl implements EmailCodeService {

    @Autowired
    private EmailCodeMapper emailCodeMapper;

    /**
     * @Description 插入邮箱验证码
     * @Date 16:42 2020/3/2
     **/
    @Override
    public void insertEmailCode(EmailCodeParam emailCodeParam) {
        emailCodeMapper.insertEmailCode(emailCodeParam);
    }

    /**
     * @Description 校验验证码
     * @Date 19:55 2020/3/2
     **/
    @Override
    public ResponseData checkCode(String emailCode, String email, String businessType) {
        EmailCodeEntity emailCodeByEmail = emailCodeMapper.getEmailCodeByEmail(email, businessType);
        if (!emailCode.equals(emailCodeByEmail.getCode())) {
            return new ResponseData().fail(EMAILCODE_NOT_CORRECT.getCode(), EMAILCODE_NOT_CORRECT.getMessage());
        }
        if (emailCodeByEmail.getExpirationTime().compareTo(new Date()) < 0) {
            return new ResponseData().fail(EMAILCODE_HAS_EXPIRED.getCode(), EMAILCODE_HAS_EXPIRED.getMessage());
        }
        return new ResponseData().success(null);
    }
}
