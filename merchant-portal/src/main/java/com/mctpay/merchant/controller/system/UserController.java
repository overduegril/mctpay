package com.mctpay.merchant.controller.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mctpay.common.base.model.PageParam;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.common.uitl.SecureUtils;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.merchant.model.dto.merchant.AccessibleMerchantDTO;
import com.mctpay.merchant.model.dto.system.LoginedUserDTO;
import com.mctpay.merchant.model.dto.system.UserDTO;
import com.mctpay.merchant.model.entity.system.UserEntity;
import com.mctpay.merchant.model.param.NewPassword;
import com.mctpay.merchant.model.param.UserParam;
import com.mctpay.merchant.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

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
    private UserService userService;

    @ApiOperation(value = "修改密码", notes = "首次登陆时修改密码", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/updatePassword")
    public ResponseData<List<UserDTO>> updatePassword(@RequestBody NewPassword NewPassword) {
        String newPwd = SecureUtils.MD5Encrypt(NewPassword.getNewPassword());
        userService.updatePassword(newPwd);
        return new ResponsePageInfo<List<UserDTO>>().success(null);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "管理员注册", notes = "管理员注册", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertUser")
    public ResponseData insertUser(@RequestBody UserParam userParam) {
        // 设置会员ID
        String id = UIdUtils.getUid().toString();
        userParam.setId(id);
        userParam.setStatus(2);
        userParam.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userParam.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userParam.setMerchantId(userEntity.getMerchantId());
        return userService.insertUser(userParam);
    }

    @ApiOperation(value = "冻结/激活管理员", notes = "冻结/激活管理员；status传值为正数则是激活的状态，负数为冻结状态，传该管理原status的相反数", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/switchUser")
    public ResponseData switchUser(@RequestParam String userId, @RequestParam Integer state) {
        return userService.switchUser(userId, state);
    }

    @ApiOperation(value = "根据输入内容查询管理员", notes = "冻结/激活管理员；status传值为正数则是激活的状态，负数为冻结状态，传该管理原status的相反数", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/listUserByInput")
    public ResponseData<List<UserDTO>> listUserByInput(String inputContent, @RequestBody PageParam pageParam) {
        Page<Object> pageInfo = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        if (!StringUtils.isEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
        List<UserDTO> userDTOs = userService.listUserByInput(inputContent);
        return new ResponsePageInfo<List<UserDTO>>().success(userDTOs, pageInfo);
    }

    @ApiOperation(value = "密码重置", notes = "管理员密码重置", httpMethod = "POST", consumes = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(
            name = "userId",
            value = "用户Id",
            required = true,
            paramType = "update",
            dataType = "String"
    )})
    @PostMapping("/resetPassword")
    public ResponseData<List<UserDTO>> resetPassword(String userId) {
        userService.resetPassword(userId);
        return new ResponsePageInfo<List<UserDTO>>().success(null);
    }

    @ApiOperation(value = "获取可以登录的商户账户", notes = "获取可以登录的商户账户", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/accounts")
    public ResponseData< List<AccessibleMerchantDTO>> listAccounts() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userEntity.getEmail();
        if (userName == null) {
            userName = userEntity.getPhoneNumber();
        }
        List<AccessibleMerchantDTO> accessibleMerchantDTOs = userService.listAccounts(userName);
        return new ResponsePageInfo<List<AccessibleMerchantDTO>>().success(accessibleMerchantDTOs);
    }

    @ApiOperation(value = "选择登录的商户", notes = "选择登录的商户", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/selectAccount")
    public ResponseData selectAccount(@RequestParam String id, HttpServletRequest request) {
        userService.selectAccount(id, request);
        return new ResponseData<>().success(null);
    }

}
