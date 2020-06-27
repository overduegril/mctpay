package com.mctpay.wallet.service.system.impl;

import com.mctpay.wallet.mapper.system.SmsCodeMapper;
import com.mctpay.wallet.model.param.SmsCodeParam;
import com.mctpay.wallet.service.system.PhoneCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 短信验证码业务
 * @Date: 2020/6/17 10:04
 */
@Service
public class PhoneCodeServiceImpl implements PhoneCodeService {

    @Autowired
    private SmsCodeMapper smsCodeMapper;

    public void insertSMSCode(SmsCodeParam smsCodeParam) {
        smsCodeMapper.insert(smsCodeParam);
    }

}
