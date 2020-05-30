package com.example.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @calss name com.example.admin.AdminServerApplication
 * @description: spring cloud admin server
 * @author: yichuan
 * @create time: 2020/05/30 11:10
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAdminServer
@EnableDiscoveryClient
@EnableEurekaClient
public class AdminServerApplication {
    //http://localhost:8099/#/applications
    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }
}
