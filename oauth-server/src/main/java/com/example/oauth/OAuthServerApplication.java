package com.example.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @calss name OAuthServerApplication
 * @description: 权限服务
 * @author: yichuan
 * @create time: 2020/05/30 21:20
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class OAuthServerApplication {
    public static void main(String[] args) {

        SpringApplication.run(OAuthServerApplication.class, args);
    }
}
