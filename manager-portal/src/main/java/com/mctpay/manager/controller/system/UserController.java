package com.mctpay.manager.controller.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.model.param.UserParam;
import com.mctpay.manager.service.system.ManagerUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 用户相关接口
 * @Date: 2020/2/24 20:08
 */
@Api(value = "系统管理", tags = "系统管理")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private ManagerUserService managerUserService;

    @ApiOperation(value = "管理员注册", notes = "管理员注册", httpMethod = "POST", consumes = "application/json")
    @RequestMapping("/insertUser")
    public ResponseData insertUser(@RequestBody UserParam userParam) {
        // 设置会员ID
        Long id = UIdUtils.getUid();
        userParam.setId(id);
        userParam.setStatus(3);
        userParam.setCreateTime(new Date());
        userParam.setUpdateTime(new Date());
        return managerUserService.insertUser(userParam);
    }

}
