package com.dongz.cms_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author dong
 * @date 2020/4/4 23:06
 * @desc
 */
@SpringBootApplication(scanBasePackages = {"com.dongz.cms_client","com.dongz.framework"})
@EntityScan("com.dongz.framework.domain.cms")//扫描实体类
public class CmsClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsClientApplication.class, args);
    }
}
