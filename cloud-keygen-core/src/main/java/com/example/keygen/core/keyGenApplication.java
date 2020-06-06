package com.example.keygen.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.annotation.Order;

/**
 * @author yichuan
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@MapperScan(basePackages = {"com.example.keygen.core.dao"})
@Order(7)
public class keyGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(keyGenApplication.class, args);
    }
}
