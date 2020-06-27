package com.mctpay.wallet.service.system;

import com.mctpay.wallet.model.param.SmsCodeParam;

/**
 * @Author: guodongwei
 * @Description: 短信验证码业务接口
 * @Date: 2020/6/17 10:04
 */
public interface PhoneCodeService {

    /**
     * 插入短信验证码
     * @param smsCodeParam
     */
    void insertSMSCode(SmsCodeParam smsCodeParam);

}
