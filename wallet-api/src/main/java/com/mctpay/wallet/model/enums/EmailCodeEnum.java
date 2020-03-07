package com.mctpay.wallet.model.enums;

import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 邮箱code枚举
 * @Date: 2020/3/2 17:09
 */
public enum EmailCodeEnum {

    REGIST("regist", "注册");

    EmailCodeEnum() {
    }

    private String emailCodeType;
    private String emailCodeName;

    private EmailCodeEnum(String emailCodeType, String emailCodeName) {
        this.emailCodeType = emailCodeType;
        this.emailCodeName = emailCodeName;
    }

    public String getEmailCodeType() {
        return emailCodeType;
    }

    public void setEmailCodeType(String emailCodeType) {
        this.emailCodeType = emailCodeType;
    }

    public String getEmailCodeName() {
        return emailCodeName;
    }

    public void setEmailCodeName(String emailCodeName) {
        this.emailCodeName = emailCodeName;
    }

}
