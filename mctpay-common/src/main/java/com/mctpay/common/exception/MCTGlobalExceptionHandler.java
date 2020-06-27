package com.mctpay.common.exception;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.constants.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.BindException;

import static com.mctpay.common.constants.ErrorCode.ARGUMENTS_VALIDATE_FAIL;

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
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public static ResponseData methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {
        BindingResult bindingResult = ex.getBindingResult();
        System.out.println(bindingResult);
        return null;
        // return resolveException(ex);
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
        String message = ex.getMessage();
        // 异常名控制
        if (className.contains("AccessDeniedException")) {
            code = ErrorCode.NON_AUTHENTICATION;
        } else if (className.contains("BindException")) {
            code = ErrorCode.ARGUMENTS_VALIDATE_FAIL;
        }

        // 异常信息控制
        if ("输入卡券不存在或已经使用".equals(message)) {
            code = ErrorCode.CARD_NOT_EXIST_OR_HAS_BEEN_USED;
        } else if ("短信验证码输入不正确".equals(message)) {
            code = ErrorCode.SMSCODE_NOT_CORRECT;
        } else if ("短信验证码已经过期".equals(message)) {
            code = ErrorCode.SMSCODE_HAS_EXPIRED;
        }

        log.debug("bug-happen---------------------------------" + ex);
        responseData.fail(code.getCode(), code.getMessage());
        return responseData;
    }
}
