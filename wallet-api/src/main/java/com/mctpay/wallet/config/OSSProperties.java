package com.mctpay.wallet.config;

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

    /**
     * bucket名字
     */
    private String bucketName;
    /**
     * 门头照路径
     */
    private String reserveKeyPrefix;
    /**
     * 营业执照路径
     */
    private String businessLicenseKeyPrefix;
    /**
     * 二维码路径
     */
    private String qrcodePath;

}
