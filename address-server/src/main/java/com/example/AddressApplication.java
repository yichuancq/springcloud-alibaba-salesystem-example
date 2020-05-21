package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yichuan
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.example.dao"})//扫描DAO
public class AddressApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddressApplication.class, args);
    }
}
