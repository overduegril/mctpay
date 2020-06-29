package com.mctpay.pos.service.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.pos.model.param.EmailCodeParam;

/**
 * @Author: guodongwei
 * @Description: 邮箱验证码业务接口
 * @Date: 2020/3/2 16:52
 */
public interface EmailCodeService {

    /**
     * @Description 插入邮箱验证码
     * @Date 16:42 2020/3/2
     **/
    void insertEmailCode(EmailCodeParam emailCodeParam);

    /**
     * @Description 校验验证码
     * @Date 19:55 2020/3/2
     **/
    ResponseData checkCode(String emailCode, String email, String businessType);
}
