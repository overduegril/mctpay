package com.mctpay.common.config;

import com.mctpay.common.uitl.SecureUtils;

/**
 * @author liqiang
 * @date 2020/4/22 09:31
 * @Description: (what)
 * (why)
 * (how)
 */
public class GlobalConstant {
    public static  final String defalutPassword= "123456";
    public static  final String defalutMd5Password= SecureUtils.MD5Encrypt(defalutPassword);
}
