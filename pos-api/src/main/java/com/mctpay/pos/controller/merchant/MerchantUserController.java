package com.mctpay.pos.controller.merchant;

import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.exceptions.ClientException;
import com.mctpay.common.base.model.ResponseData;
import com.mctpay.common.uitl.EmailUtils;
import com.mctpay.common.uitl.SMSUtils;
import com.mctpay.pos.config.EmailProperties;
import com.mctpay.pos.config.PhoneProperties;
import com.mctpay.pos.enums.EmailCodeEnum;
import com.mctpay.pos.model.enums.PhoneCodeEnum;
import com.mctpay.pos.model.param.EmailCodeParam;
import com.mctpay.pos.model.param.SmsCodeParam;
import com.mctpay.pos.service.merchant.MerchantUserService;
import com.mctpay.pos.service.system.EmailCodeService;
import com.mctpay.pos.service.system.PhoneCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

import static com.mctpay.common.constants.ErrorCode.SMS_SEND_FAIL;

/**
 * @Author: guodongwei
 * @Description: 商户控制层
 * @Date: 2020/5/23 20:59
 */
@Api(value = "商户用户管理", tags = "商户用户管理")
@RestController
@RequestMapping("merchantuser")
@Log4j2
public class MerchantUserController {

    @Autowired
    private EmailCodeService emailCodeService;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private PhoneProperties phoneProperties;

    @Autowired
    private PhoneCodeService phoneCodeService;

    @Autowired
    private MerchantUserService merchantUserService;

    @ApiOperation(value = "发送邮箱验证码", notes = "发送邮箱验证码", httpMethod = "GET", consumes = "application/json")
    @GetMapping("/sendEmailCode")
    public ResponseData sendEmailCode(String email, Integer language, EmailCodeEnum emailCodeEnum){
        String randomString = RandomUtil.randomString(6);
        String subject = "";
        // 存储验证码，等待用户输入后进行校验
        EmailCodeParam emailCodeParam = new EmailCodeParam();
        if ("findBack-password".equalsIgnoreCase(emailCodeEnum.getEmailCodeType())) {
            // 默认主题
            subject = emailProperties.getRegistChineseSubject();
            if (language != null && language == 1) {
                subject = emailProperties.getRegistEnglishSubject();
            }
            emailCodeParam.setBusinessType(EmailCodeEnum.FINDBACKPASSWORD.getEmailCodeType());
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
            smsCodeParam.setBusinessType(PhoneCodeEnum.FINDBACKPASSWORD.getPhoneCodeType());
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


    @ApiOperation(value = "找回用户密码", notes = "找回用户密码", httpMethod = "POST", consumes = "application/json")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "type", value = "类型 0 邮箱找回 1 电话号码找回" ,required = true),
                    @ApiImplicitParam(name = "number", value = "电话号码或者邮箱" ,required = true),
                    @ApiImplicitParam(name = "code", value = "验证码" ,required = true),
                    @ApiImplicitParam(name = "password", value = "新密码" ,required = true)
            }
    )
    @PostMapping("/updatefindBackPassword")
    public ResponseData updatefindBackPassword(Integer type, String number, String password,String code){

        return merchantUserService.updatefindBackPassword( type, number, password, code);
    }

}
