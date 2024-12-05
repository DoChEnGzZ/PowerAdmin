package com.chengnianzhi.poweradmin_api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtils {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // 对密码明文进行编码
    public static String encode(String plainText) {
        return bCryptPasswordEncoder.encode(plainText);
    }

    // 判断密码是否跟数据库中存储的匹配
    public static boolean matches(String rawPwd, String encodedPwd) {
        return bCryptPasswordEncoder.matches(rawPwd, encodedPwd);
    }
}
