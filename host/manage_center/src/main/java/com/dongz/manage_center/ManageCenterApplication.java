package com.dongz.manage_center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author dong
 * @date 2020/4/6 14:10
 * @desc
 */
@SpringBootApplication
@EnableEurekaServer
public class ManageCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCenterApplication.class, args);
    }
}
