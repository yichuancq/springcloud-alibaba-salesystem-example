package com.example.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @calss name OAuthServerApplication
 * @description: 权限服务
 * @author: yichuan
 * @create time: 2020/05/30 21:20
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
@EnableResourceServer
@Slf4j

public class OAuthServerApplication implements CommandLineRunner {
    //https://blog.csdn.net/Victor_An/article/details/81510874
    //https://blog.csdn.net/yileilaile/article/details/102495847
    public static void main(String[] args) {
        SpringApplication.run(OAuthServerApplication.class, args);

    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

    }
}
