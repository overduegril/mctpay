package com.mctpay.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: guodongwei
 * @Description: 雪花算法配置类
 * @Date: 2020/2/21 17:49
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "uid-generator")
public class UidGeneratorConfig {

    /**
     * 工作ID (0~31)
     */
    private long workId = 0;
    /**
     * 数据中心ID (0~31)
     */
    private long centerId = 0;

}
