package com.mctpay.common.exception;

import lombok.Data;

/**
 * @author liqiang
 * @date 2020/4/22 11:37
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class BusinessException extends Exception {
    private Integer code;
    private String description;
    public BusinessException(Integer code,String description){
        this.code=code;
        this.description=description;
    }

}
