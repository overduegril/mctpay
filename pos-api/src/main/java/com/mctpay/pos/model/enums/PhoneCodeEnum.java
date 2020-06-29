package com.mctpay.pos.model.enums;

/**
 * @Author: guodongwei
 * @Description: 手机code枚举
 * @Date: 2020/3/2 17:09
 */
public enum PhoneCodeEnum {

    FINDBACKPASSWORD("findBack-password", "找回密码");

    PhoneCodeEnum() {
    }

    private String phoneCodeType;
    private String phoneCodeName;

    private PhoneCodeEnum(String phoneCodeType, String phoneCodeName) {
        this.phoneCodeType = phoneCodeType;
        this.phoneCodeName = phoneCodeName;
    }

    public String getPhoneCodeType() {
        return phoneCodeType;
    }

    public void setPhoneCodeType(String phoneCodeType) {
        this.phoneCodeType = phoneCodeType;
    }

    public String getEmailCodeName() {
        return phoneCodeName;
    }

    public void setEmailCodeName(String phoneCodeName) {
        this.phoneCodeName = phoneCodeName;
    }

}
