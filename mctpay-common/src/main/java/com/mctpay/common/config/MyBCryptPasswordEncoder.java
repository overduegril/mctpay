package com.mctpay.common.config;

import com.mctpay.common.uitl.SecureUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @Author: guodongwei
 * @Description: 重写密码比对
 * @Date: 2020/2/26 14:09
 */
@Component("myBCryptPasswordEncoder")
public class MyBCryptPasswordEncoder extends BCryptPasswordEncoder {

    private Pattern BCRYPT_PATTERN;

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        this.BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
        if (encodedPassword != null && encodedPassword.length() != 0) {
            if (!this.BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
                return false;
            } else {
                return BCrypt.checkpw(SecureUtils.MD5Encrypt(rawPassword.toString()), encodedPassword);
            }
        } else {
            return false;
        }
    }

}
