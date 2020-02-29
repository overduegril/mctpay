package com.mctpay.wallet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: guodongwei
 * @Description: 钱包启动类诶
 * @Date: 2020/2/27 21:37
 */
@SpringBootApplication(scanBasePackages = {"com.mctpay.wallet.*", "com.mctpay.common.exception", "com.mctpay.common.config"})
@MapperScan("com.mctpay.wallet.mapper.*")
public class WalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);
    }

}
