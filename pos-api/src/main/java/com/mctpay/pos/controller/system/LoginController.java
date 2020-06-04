package com.mctpay.pos.controller.system;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.base.model.ResponsePageInfo;
import com.mctpay.pos.model.dto.system.AccessibleMerchantDTO;
import com.mctpay.pos.model.entity.system.UserEntity;
import com.mctpay.pos.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 系统管理(登陆相关)
 * @Date: 2020/5/12 16:47
 */
@Api(value = "系统管理", tags = "系统管理")
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private UserService userService;

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
