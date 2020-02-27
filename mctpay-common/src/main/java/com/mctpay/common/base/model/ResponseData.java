package com.mctpay.common.base.model;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: guodongwei
 * @Description: 基础返回
 * @Date: 2020/2/24 13:58
 */
@ApiModel(value = "格式返回")
public class ResponseData<T> {
    @ApiModelProperty(value = "成功为0，失败为1")
    Integer result;
    @ApiModelProperty(value = "返回数据")
    T data;
    @ApiModelProperty(value = "错误码")
    Integer errorCode;
    @ApiModelProperty(value = "错误信息")
    String errorMsg;

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
