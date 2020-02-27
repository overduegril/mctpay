package com.mctpay.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: guodongwei
 * @Description: 雪花算法配置类
 * @Date: 2020/2/21 17:49
 */
@Configuration
@PropertySource("classpath:config/uidgenerator.properties")
public class UidGeneratorConfig {

    /**
     * 工作ID (0~31)
     */
    public static long workId;
    /**
     * 数据中心ID (0~31)
     */
    public static long centerId;

    @Value("${workId}")
    public void setWorkId(long workId) {
        UidGeneratorConfig.workId = workId;
    }
    @Value("${centerId}")
    public void setCenterId(long centerId) {
        UidGeneratorConfig.centerId = centerId;
    }
}
