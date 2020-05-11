package com.mctpay.manager;

import cn.hutool.core.date.DateUtil;
import cn.hutool.cron.CronUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 管理平台启动类
 * @Date: 2020/2/23 13:12
 */
@SpringBootApplication(scanBasePackages = {"com.mctpay.manager.*", "com.mctpay.common.exception", "com.mctpay.common.config"})
@MapperScan("com.mctpay.manager.mapper.*")
public class ManagerAppplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerAppplication.class, args);
        CronUtil.start();
    }

}
