package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
 * @Date: 2019-05-08
 */
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
