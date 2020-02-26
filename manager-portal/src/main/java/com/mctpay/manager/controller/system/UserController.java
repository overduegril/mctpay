package com.mctpay.manager.controller.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.manager.model.entity.system.UserEntity;
import com.mctpay.manager.model.param.UserParam;
import com.mctpay.manager.service.system.ManagerUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "管理员注册", notes = "管理员注册", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertUser")
    public ResponseData insertUser(@RequestBody UserParam userParam) {
        // 设置会员ID
        Long id = UIdUtils.getUid();
        userParam.setId(id);
        userParam.setStatus(3);
        userParam.setCreateTime(new Date());
        userParam.setUpdateTime(new Date());
        return managerUserService.insertUser(userParam);
    }

    @ApiOperation(value = "冻结/激活管理员", notes = "冻结/激活管理员-status传值为1，表示激活管理员，-1为冻结管理员", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/switchUser")
    public ResponseData switchUser(@RequestParam Long userId, @RequestParam Integer state) {
        return managerUserService.switchUser(userId, state);
    }

}
