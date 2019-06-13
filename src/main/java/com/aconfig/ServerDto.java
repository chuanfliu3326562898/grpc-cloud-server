package com.aconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * <p>
 * @Date: 2019-05-11
 */
@Data
@Component
@ConfigurationProperties(prefix= "rpc.server")
public class ServerDto {
    private String name;
    private String ip;
    private String port;
    public ServerDto(){
        System.out.println("SERVER DTO INITED");
    }
}
