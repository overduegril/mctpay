package com.mctpay.common.exception;

import com.mctpay.common.constants.ErrorCode;

/**
 * @Author: guodongwei
 * @Description: 自定义异常
 * @Date: 2020/6/17 15:49
 */
public class MCTException extends RuntimeException {


    private int code = ErrorCode.ERROR.getCode();

    public MCTException() {

    }

    public MCTException(String msg) {
        super(msg);
    }

    public MCTException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public MCTException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
