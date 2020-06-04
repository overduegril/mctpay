package com.mctpay.wallet.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: guodongwei
 * @Description: 邮箱参数配置
 * @Date: 2020/3/2 15:09
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "email-properties")
public class EmailProperties {

    /**
     * 英文用户注册邮件注册主题
     */
    private String registEnglishSubject;

    /**
     * 汉语用户注册邮件注册主题
     */
    private String registChineseSubject;

    /**
     * 英文用户邮件修改密码主题
     */
    private String updatePasswordEnglishSubject;

    /**
     * 汉语用户邮件修改密码主题
     */
    private String  updatePasswordChineseSubject;

}
