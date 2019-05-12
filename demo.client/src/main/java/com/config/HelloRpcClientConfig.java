package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
 * @Date: 2019-05-12
 */
@Configuration
public class HelloRpcClientConfig {
    @Bean
    public DemoClient getRpcDemoCient(){
        String serviceName="hello";
        return DemoClient();
    }
}
