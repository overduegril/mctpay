package com.mctpay.manager.controller.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.model.param.UserParam;
import com.mctpay.manager.service.system.ManagerUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 用户相关接口
 * @Date: 2020/2/24 20:08
 */
public class UserController {

    @Autowired
    private ManagerUserService managerUserService;

    @ApiOperation(value = "积分等级设置", notes = "积分等级设置", httpMethod = "GET")
    @RequestMapping("/insertUser")
    public ResponseData insertUser(UserParam userParam) {
        // 设置会员ID
        Long id = UIdUtils.getUid();
        userParam.setId(id);
        userParam.setStatus(3);
        userParam.setCreateTime(new Date());
        userParam.setUpdateTime(new Date());
        managerUserService.insertUser(userParam);
        return null;
    }


}
