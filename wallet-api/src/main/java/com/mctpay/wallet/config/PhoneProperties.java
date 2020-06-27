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
@ConfigurationProperties(prefix = "phone-properties")
public class PhoneProperties {

    /**
     * 英文用户邮箱绑定
     */
    private String bindingEnglishTemplate;

    /**
     * 汉语用户手机号绑定
     */
    private String bindingChineseTemplate;

}
