package com.example.oauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @calss name CustomPasswordEncoder
 * @description:
 * @author: yichuan
 * @create time: 2020/06/05 22:01
 */
@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        log.info("charSequence :{}", charSequence);
        log.info("s :{}", s);
        return s.equals(charSequence.toString());
    }

}