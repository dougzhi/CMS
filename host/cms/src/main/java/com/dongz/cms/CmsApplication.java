package com.dongz.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author dong
 * @date 2020/3/31 22:44
 * @desc
 */
@SpringBootApplication(scanBasePackages = {"com.dongz.api","com.dongz.cms","com.dongz.framework"})
@EntityScan("com.dongz.framework.domain.cms")//扫描实体类
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
