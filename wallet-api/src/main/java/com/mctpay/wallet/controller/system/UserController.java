package com.mctpay.wallet.controller.system;

import cn.hutool.core.util.RandomUtil;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.EmailUtils;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.wallet.config.EmailProperties;
import com.mctpay.wallet.model.param.EmailCodeParam;
import com.mctpay.wallet.model.param.UserParam;
import com.mctpay.wallet.service.system.EmailCodeService;
import com.mctpay.wallet.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

import static com.mctpay.common.constants.ErrorCode.EMAIL_HAS_BEEN_USED;
import static com.mctpay.wallet.model.enums.EmailCodeEnum.REGIST;

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
    private EmailProperties emailProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailCodeService emailCodeService;

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertUser")
    public ResponseData insertUser(@RequestBody UserParam userParam) {
        // 设置会员ID
        Long id = UIdUtils.getUid();
        userParam.setId(id);
        userParam.setNickname("mct" + RandomUtil.randomString(6));
        userParam.setStatus(1);
        userParam.setCreateTime(new Date());
        userParam.setUpdateTime(new Date());
        return userService.insertUser(userParam);
    }

    @ApiOperation(value = "发送邮箱验证码", notes = "发送邮箱验证码", httpMethod = "GET", consumes = "application/json")
    @GetMapping("/sendEmailCode")
    public ResponseData sendEmailCode(String email, Integer language) {
        // 确认邮箱是否被使用过
        Integer integer = userService.countEmail(email);
        if (integer != 0) {
            return new ResponseData<>().fail(EMAIL_HAS_BEEN_USED.getCode(), EMAIL_HAS_BEEN_USED.getMessage());
        }
        String randomString = RandomUtil.randomString(6);
        // 默认主题
        String subject = emailProperties.getRegistChineseSubject();
        if (language != null && language == 1) {
            subject = emailProperties.getRegistEnglishSubject();
        }
        EmailUtils.sendRegistEmail(email, subject, randomString);
        // 存储验证码，等待用户输入后进行校验
        EmailCodeParam emailCodeParam = new EmailCodeParam();
        emailCodeParam.setCreateTime(new Timestamp(System.currentTimeMillis()));
        emailCodeParam.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        emailCodeParam.setCode(randomString);
        emailCodeParam.setToEmail(email);
        emailCodeParam.setStatus(1);
        emailCodeParam.setBusinessType(REGIST.getEmailCodeType());
        emailCodeParam.setExpirationTime(new Date(System.currentTimeMillis() + 120000));
        emailCodeService.insertEmailCode(emailCodeParam);
        return new ResponseData().success(null);
    }

    /**
     * @Description 校验验证码
     * @Date 19:31 2020/3/2
     **/
    @ApiOperation(value = "校验验证码", notes = "校验验证码,regist为注册验证码", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/checkCode")
    public ResponseData checkCode(@RequestParam String emailCode, @RequestParam String email, @RequestParam String businessType) {
        ResponseData responseData = emailCodeService.checkCode(emailCode, email, businessType);
        return responseData;
    }
}
