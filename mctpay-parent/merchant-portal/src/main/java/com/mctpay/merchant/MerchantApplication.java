package com.mctpay.merchant;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: guodongwei
 * @Description: 商户端启动类
 * @Date: 2020/2/23 12:34
 */
@SpringBootApplication(scanBasePackages = {"com.mctpay.*", "com.mctpay.common.exception", "com.mctpay.common.config"})
@MapperScan("com.mctpay.manager.mapper.*")
public class MerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchantApplication.class, args);
    }

}
