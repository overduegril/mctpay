package com.mctpay.wallet.controller.system;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.exceptions.ClientException;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.constants.ErrorCode;
import com.mctpay.common.exception.MCTException;
import com.mctpay.common.uitl.EmailUtils;
import com.mctpay.common.uitl.OSSUtils;
import com.mctpay.common.uitl.SMSUtils;
import com.mctpay.common.uitl.UIdUtils;
import com.mctpay.wallet.config.EmailProperties;
import com.mctpay.wallet.config.OSSProperties;
import com.mctpay.wallet.config.PhoneProperties;
import com.mctpay.wallet.model.dto.point.PointInfoDTO;
import com.mctpay.wallet.model.entity.system.UserEntity;
import com.mctpay.wallet.model.enums.EmailCodeEnum;
import com.mctpay.wallet.model.enums.PhoneCodeEnum;
import com.mctpay.wallet.model.param.CheckCodeParam;
import com.mctpay.wallet.model.param.EmailCodeParam;
import com.mctpay.wallet.model.param.SmsCodeParam;
import com.mctpay.wallet.model.param.UserParam;
import com.mctpay.wallet.service.system.EmailCodeService;
import com.mctpay.wallet.service.system.PhoneCodeService;
import com.mctpay.wallet.service.system.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import static com.mctpay.common.constants.ErrorCode.SMS_SEND_FAIL;
import static com.mctpay.wallet.model.enums.EmailCodeEnum.REGIST;
import static com.mctpay.wallet.model.enums.EmailCodeEnum.UPDATE_PASSWORD;
import static com.mctpay.wallet.model.enums.PhoneCodeEnum.USERS_BINDING;

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

    @Autowired
    private PhoneCodeService phoneCodeService;

    @Autowired
    private OSSProperties oSSProperties;

    @Autowired
    private PhoneProperties phoneProperties;

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/insertUser")
    public ResponseData insertUser(@RequestBody UserParam userParam) throws IOException {
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
    public ResponseData sendEmailCode(String email, Integer language, EmailCodeEnum emailCodeEnum ) {
        String randomString = RandomUtil.randomString(6);
        String subject = "";
        // 存储验证码，等待用户输入后进行校验
        EmailCodeParam emailCodeParam = new EmailCodeParam();
        if ("regist".equalsIgnoreCase(emailCodeEnum.getEmailCodeType())) {
            // 默认主题
             subject = emailProperties.getRegistChineseSubject();
            if (language != null && language == 1) {
                subject = emailProperties.getRegistEnglishSubject();
            }
            emailCodeParam.setBusinessType(REGIST.getEmailCodeType());
        }else if ("update-password".equalsIgnoreCase(emailCodeEnum.getEmailCodeType())) {
            // 默认主题
            subject = emailProperties.getUpdatePasswordChineseSubject();
            if (language != null && language == 1) {
                subject = emailProperties.getUpdatePasswordEnglishSubject();
            }
            emailCodeParam.setBusinessType(UPDATE_PASSWORD.getEmailCodeType());
        }
        EmailUtils.sendRegistEmail(email, subject, randomString);
        emailCodeParam.setCreateTime(new Timestamp(System.currentTimeMillis()));
        emailCodeParam.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        emailCodeParam.setCode(randomString);
        emailCodeParam.setToEmail(email);
        emailCodeParam.setStatus(1);
        emailCodeParam.setExpirationTime(new Date(System.currentTimeMillis() + 120000));
        emailCodeService.insertEmailCode(emailCodeParam);
        return new ResponseData().success(null);
    }

    /**
     * @Description 校验验证码
     * @Date 19:31 2020/3/2
     **/
    @ApiOperation(value = "校验验证码", notes = "校验验证码,regist为注册验证码,update-password忘记/修改密码", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/checkCode")
    public ResponseData checkCode(@RequestBody CheckCodeParam checkCodeParam) {
        ResponseData responseData = emailCodeService.checkCode(checkCodeParam.getEmailCode(),checkCodeParam.getEmail(), checkCodeParam.getBusinessType());
        return responseData;
    }

    /**
     * @Description 修改昵称
     * @Date 19:31 2020/5/25
     **/
    @ApiOperation(value = "修改昵称", notes = "修改昵称", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/updateNickname")
    public ResponseData updateNickname(@RequestParam String newNickname){
        // 获取此时登陆的用户ID作为真实会员ID
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseData responseData = userService.updateNickname(userEntity.getId(),newNickname);
        return responseData;
    }
    /**
     * @Description 修改昵称
     * @Date 19:31 2020/5/25
     **/
    @ApiOperation(value = "修改头像", notes = "修改头像", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/updateHeadpicture")
    public ResponseData updateHeadpicture(@RequestParam MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        String businessLicenseUrl = OSSUtils.uploadFileInputStream(oSSProperties.getBucketName(), oSSProperties.getBusinessLicenseKeyPrefix() + file.getOriginalFilename(), inputStream);
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updateHeadpicture(businessLicenseUrl,userEntity.getId());
        return new ResponseData<String>().success(businessLicenseUrl);
    }

    /**
     * @Description 修改昵称
     * @Date 19:31 2020/5/25
     **/
    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/updatePassword")
    public ResponseData updatePassword(@RequestParam String newPassword,@RequestParam String oldPassword){
        // 获取此时登陆的用户ID作为真实会员ID
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.updatePassword(newPassword,oldPassword,userEntity.getId());
    }

    /**
     * @Description H获取登陆用户的积分信息
     * @Date 19:31 2020/5/25
     **/
    @ApiOperation(value = "积分信息", notes = "积分信息", httpMethod = "GET", consumes = "application/json")
    @GetMapping("/point-info")
    public ResponseData<PointInfoDTO> getPointInfo(){
        // 获取此时登陆的用户ID作为真实会员ID
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PointInfoDTO pointInfo = userService.getPointInfo(userEntity.getId());
        return new ResponseData<PointInfoDTO>().success(pointInfo);
    }

    @ApiOperation(value = "忘记密码修改密码", notes = "忘记密码修改密码", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/forget-password")
    public ResponseData forgetPassword(@RequestParam("email") String email, @RequestParam("emailCode") String emailCode, @RequestParam("newPassword")  String newPassword){
        userService.forgetPassword(email, emailCode, newPassword);
        return new ResponseData().success(null);
    }

    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码", httpMethod = "GET", consumes = "application/json")
    @GetMapping("/sendSMSCode")
    public ResponseData sendPhoneCode(String phoneNumber, Integer language, PhoneCodeEnum phoneCodeEnum ) throws ClientException {
        String randomNumbers = RandomUtil.randomNumbers(6);
        String template = "";
        // 存储验证码，等待用户输入后进行校验
        SmsCodeParam smsCodeParam = new SmsCodeParam();
        if ("users_binding".equalsIgnoreCase(phoneCodeEnum.getPhoneCodeType())) {
            // 默认模板
            template = phoneProperties.getBindingChineseTemplate();
            if (language != null && language == 1) {
                template = phoneProperties.getBindingEnglishTemplate();
            }
            smsCodeParam.setBusinessType(USERS_BINDING.getPhoneCodeType());
            SMSUtils.sendMessage(phoneNumber, randomNumbers, template);
        } else {
            return new ResponseData().fail(SMS_SEND_FAIL.getCode(), SMS_SEND_FAIL.toString());
        }
        smsCodeParam.setCreateTime(new Date());
        smsCodeParam.setUpdateTime(new Date());
        smsCodeParam.setCode(randomNumbers);
        smsCodeParam.setPhoneNumber(phoneNumber);
        smsCodeParam.setStatus(1);
        smsCodeParam.setExpirationTime(new Date(System.currentTimeMillis() + 300000));
        phoneCodeService.insertSMSCode(smsCodeParam);
        return new ResponseData().success(null);
    }

    @ApiOperation(value = "用户绑定手机号", notes = "用户绑定手机号", httpMethod = "POST", consumes = "application/json")
    @PostMapping("/binding-phone")
    public ResponseData bindingPhone(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("smsCode") String smsCode) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseData responseData = userService.bindingPhoneNumber(userEntity.getId(), phoneNumber, smsCode);
        return responseData;
    }
}
