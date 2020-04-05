package com.dongz.course_manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author dong
 * @date 2020/4/5 12:48
 * @desc
 */
@SpringBootApplication(scanBasePackages = {"com.dongz.course_manage","com.dongz.api","com.dongz.framework"})
@EntityScan("com.dongz.framework.domain.course")//扫描实体类
public class CourseManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseManageApplication.class, args);
    }
}
