package com.mctpay.common.base.model;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: guodongwei
 * @Description: 基础返回
 * @Date: 2020/2/24 13:58
 */
@Slf4j
public class ResponseData<T> {
    private Integer result;
    private T data;
    private Integer errorCode;
    private String errorMsg;

    public ResponseData() {
    }

    public ResponseData<T> success(T data) {
        this.result = 0;
        this.data = data;
        return this;
    }

    public ResponseData<T> fail(Integer errCode, String errMsg) {
        this.result = 1;
        this.errorMsg = errMsg;
        this.errorCode = errCode;
        log.debug(errCode + ":" + errMsg);
        return this;
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
