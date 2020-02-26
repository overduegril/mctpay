package com.mctpay.common.exception;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.constants.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: guodongwei
 * @Description: 全局异常控制
 * @Date: 2020/2/24 14:27
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class MCTGlobalExceptionHandler {

    /**
     * @Description 认证异常处理
     * @Date 14:34 2020/2/24
     **/
    @ExceptionHandler({AuthenticationException.class})
    public static ResponseData authenticationException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        return resolveException(ex);
    }

    /**
     * @Description 其它异常
     * @Date 14:35 2020/2/24
     **/
    @ExceptionHandler({Exception.class})
    public static ResponseData exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        return resolveException(ex);
    }

    /**
     * @Description 解析异常
     * @Date 15:06 2020/2/24
     **/
    public static ResponseData resolveException(Exception ex) {
        ResponseData responseData = new ResponseData();
        ErrorCode code = ErrorCode.ERROR;
        // 获取异常名
        String className = ex.getClass().getName();
        if (className.contains("non_authentication")) {
            code = ErrorCode.NON_AUTHENTICATION;
        } else if (className.contains("DisabledException")) {
            // -- TODO 如果有其它错误在这里解析。设置规定格式让前端进行规则处理
        }
        log.debug("bug-happen---------------------------------" + ex.getMessage());
        responseData.fail(code.getCode(), code.getMessage());
        return responseData;
    }

}
