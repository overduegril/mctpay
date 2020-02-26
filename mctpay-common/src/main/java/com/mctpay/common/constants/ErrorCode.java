package com.mctpay.common.constants;

/**
 * @Author: guodongwei
 * @Description: 错误码，用于全局异常控制
 * @Date: 2020/2/24 15:01
 */
public enum ErrorCode {
    /**
     * 账号错误
     */
    ERROR(5000, "error"),
    /**
     * 未进行身份验证
     */
    NON_AUTHENTICATION(3000, "non_authentication"),
    ACCOUNT_FROZEN(3001, "account_frozen"),
    USERNAME_OR_PASSWORD_ERR(3002, "username_or_password_err"),
    /**
     * 邮箱已经被使用
     */
    EMAIL_HAS_BEEN_USED(3003, "email_has_been_used"),
    /**
     * 手机号已经被使用
     */
    PHONENUM_HAS_BEEN_USED(3004, " phonenum_has_been_used"),
    /**
     * 用户名已经被使用
     */
    USERNAME_HAS_BEEN_USED(3005, " username_has_been_used");

    private int code;
    private String message;

    ErrorCode() {
    }

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
