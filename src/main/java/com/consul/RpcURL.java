package com.consul;

import lombok.Data;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * <p>
 * @Date: 2019-05-29
 */
@Data
public class RpcURL {
    //http://test-java-consul.baijiahulian.com:8500/ui/dc1/services
    private String registryHost = "127.0.0.1";//"test-java-consul.baijiahulian.com";
    private String host="127.0.0.1";
    private Integer registryPort=8500;
    private Integer port=50051;
}
