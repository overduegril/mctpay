package com.mctpay.pos.service.system.impl;


import com.mctpay.pos.mapper.system.SmsCodeMapper;
import com.mctpay.pos.model.param.SmsCodeParam;
import com.mctpay.pos.service.system.PhoneCodeService;
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
