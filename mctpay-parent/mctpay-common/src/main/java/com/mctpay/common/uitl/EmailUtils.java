package com.mctpay.common.uitl;

import cn.hutool.extra.mail.MailUtil;

/**
 * @Author: guodongwei
 * @Description: 邮箱工具类
 * @Date: 2020/3/2 15:25
 */
public class EmailUtils {

    /**
     * @Description 发送邮箱工具类
     * @Date 15:26 2020/3/2
     **/
    public static void sendRegistEmail(String email, String subject, String randomString) {
        MailUtil.send(email, subject, randomString, false);
    }

}
