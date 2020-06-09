package com.example.address.config;

/**
 * @calss name OAuth2ResourceConfig
 * @description:
 * @author: yichuan
 * @create time: 2020/06/06 02:22
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/***
 *
 * 只需使用@EnableResourceServer注解便可以将Spring Boot 2.0工程配置为资源服务器，
 * 并且只需新建一个ResourceServerConfigurerAdapter的子类， 便可以开启资源服务器的自定义配置
 * @author yichuan
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:9001/auth/oauth/check_token");
        tokenService.setClientId("android");
        tokenService.setClientSecret("123456");
        return tokenService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests().anyRequest().permitAll();
    }
}
