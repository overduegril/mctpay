package com.mctpay.merchant.controller;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.dto.merchantuser.FindByEmailDtO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author liqiang
 * @date 2020/4/22 10:41
 * @Description: (what)
 * (why)
 * (how)
 */
public class BaseController {
    public FindByEmailDtO  getCurrentUserInfo(){
        FindByEmailDtO findByEmailDtO= (FindByEmailDtO)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByEmailDtO;
    }
    public <T>ResponseData success(T data) {
        ResponseData<T> result=new ResponseData<T>();
        result.success(data);
        return result;
    }
    public <T>ResponseData success() {
        ResponseData<T> result=new ResponseData<T>();
        result.success(null);
        return result;
    }

    public <T>ResponseData fail(Integer errCode, String errMsg) {
        ResponseData<T> result=new ResponseData<T>();
        return  result.fail(errCode,errMsg);
    }
}
