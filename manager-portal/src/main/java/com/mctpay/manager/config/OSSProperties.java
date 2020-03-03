package com.mctpay.manager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: guodongwei
 * @Description: Oss相关配置
 * @Date: 2020/2/27 17:52
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun-oss")
public class OSSProperties {

    private String bucketName;

    private String keyPrefix;

    private String qrcodePath;

}
