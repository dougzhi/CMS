package com.dongz.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dong
 * @date 2020/3/31 22:44
 * @desc
 */
@SpringBootApplication
@EntityScan("com.dongz.framework.domain.cms")//扫描实体类
@ComponentScan(basePackages = {"com.dongz.api"})//扫描接口
@ComponentScan(basePackages = {"com.dongz.cms"})//扫描接口
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
