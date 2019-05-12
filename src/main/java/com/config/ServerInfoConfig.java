/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.config;

import com.client.DemoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author macrohuang
 * @version version
 * @title ProdConfig
 * @desc description
 * @date Dec 5, 2017
 */
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = {"classpath:server.properties"})
public class ServerInfoConfig {

    @Bean
   // @ConfigurationProperties(prefix = "com.grpc.server")
    public ServerDto rpcServerDto() {
        return new ServerDto();
    }

    @Bean
    // @ConfigurationProperties(prefix = "com.grpc.server")
    public DemoClient helloRpcDemoClient() {
        return new DemoClient();
    }

}
