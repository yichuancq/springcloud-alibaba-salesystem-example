package com.example.film;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author yichuan
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient //本服务启动后，会自动注册到 Eureka 服务中
@MapperScan(basePackages = {"com.example.film.dao"})//扫描DAO
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(7)
public class FilmApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmApplication.class, args);
    }
}
