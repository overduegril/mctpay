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
    ACCOUNT_DISABLED(3001, "account_disabled"),
    USERNAME_NOT_FOUND(3002, "username_not_found");

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
