/**
 * Baijiahulian.com Inc. Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.aconfig;

import com.client.DemoClientProxyBase;
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

//    @Bean
    // @ConfigurationProperties(prefix = "com.grpc.server")
//    @Order(1)
//    public ServerDto rpcServerDto() {
//        return new ServerDto();
//    }

    @Bean
    public DemoClientProxyBase helloRpcDemoClientProxyBase() {
        return new DemoClientProxyBase();
    }

}
