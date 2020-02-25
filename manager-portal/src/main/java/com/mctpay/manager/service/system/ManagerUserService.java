package com.mctpay.manager.service.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.manager.model.param.UserParam;

/**
 * @Author: guodongwei
 * @Description: 用户相关接口
 * @Date: 2020/2/24 20:42
 */
public interface ManagerUserService {

    /**
     * @Description 注册管理员
     * @Date 20:45 2020/2/24
     **/
    ResponseData insertUser(UserParam userParam);

}
