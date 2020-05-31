package com.example.oauth.config;

import com.example.oauth.util.DigestUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yichuan
 */
public class NoEncryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return (String) charSequence;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            if (rawPassword == null || encodedPassword == null) {
                return false;
            }
            //加密
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            return encodedPassword.equals(encoder.encode(rawPassword));
            return encodedPassword.equals(DigestUtil.encrypt((String) rawPassword));
        } catch (Exception e) {
            return false;
        }
    }
}
