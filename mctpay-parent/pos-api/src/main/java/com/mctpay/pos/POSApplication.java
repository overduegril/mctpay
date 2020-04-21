package com.mctpay.pos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: guodongwei
 * @Description: pos启动类
 * @Date: 2020/2/27 21:36
 */
@SpringBootApplication(scanBasePackages = {"com.mctpay.pos.*", "com.mctpay.common.exception", "com.mctpay.common.config"})
@MapperScan("com.mctpay.pos.mapper.*")
public class POSApplication {

    public static void main(String[] args) {
        SpringApplication.run(POSApplication.class, args);
    }

}
