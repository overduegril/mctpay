package com.mctpay.pos.enums;

/**
 * @Author: chenshubiao
 * @Description: 邮箱code枚举
 * @Date: 2020/6/29 11:00
 */
public enum EmailCodeEnum {

    FINDBACKPASSWORD("findBack-password", "找回秘密");


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
