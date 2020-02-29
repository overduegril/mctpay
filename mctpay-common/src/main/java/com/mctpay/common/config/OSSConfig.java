package com.mctpay.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: guodongwei
 * @Description: oss参数配置
 * @Date: 2020/2/27 13:56
 */
@Configuration
@PropertySource("classpath:config/oss.properties")
public class OSSConfig {
    /**
     * 阿里云API的密钥Access Key ID
     */
    public static String accessKeyId;
    /**
     * 阿里云API的密钥Access Key Secret
     */
    public static String accessKeySecret;
    /**
     * 阿里云API的内或外网域名
     */
    public static String endpoint;
    /**
     * 阿里云API的bucket名称
     */
    public static String bucketName;

    @Value("${accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        OSSConfig.accessKeyId = accessKeyId;
    }

    @Value("${accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        OSSConfig.accessKeySecret = accessKeySecret;
    }

    @Value("${endpoint}")
    public void setEndpoint(String endpoint) {
        OSSConfig.endpoint = endpoint;
    }

    @Value("${bucketName}")
    public void setBucketName(String bucketName) {
        OSSConfig.bucketName = bucketName;
    }

}
