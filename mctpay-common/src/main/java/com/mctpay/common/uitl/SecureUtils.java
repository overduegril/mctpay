package com.mctpay.common.uitl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * @Author: guodongwei
 * @Description: 加密解密工具类
 * @Date: 2020/2/25 19:35
 */
public class SecureUtils {

    /**
     * @Description
     * @Date 19:36 2020/2/25
     **/
    public static String MD5Encrypt(String content) {
        String md5Content = SecureUtil.md5(content);
        return md5Content;
    }
}
