package com.client;

import com.aconfig.ServerDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * <p>
 * @Date: 2019-05-12
 */
@Component
@Slf4j
@DependsOn("consulDiscoveryClient")
public class DemoClientProxyBase{
    @Autowired
    protected ServerDto serverDto;

    protected ManagedChannel channel;
    @Autowired
    private ConsulDiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Value("${application.name}")
    private String appName;

    public DemoClientProxyBase(){
        System.out.println("DemoClientProxyBase inited");
    }

    @PostConstruct
    public void init(){
        System.out.println("DemoClientProxyBase postInited");
    }

    public void start() {
        if(channel==null){
            log.info("DemoClient InitializingBean started");
            ServiceInstance ipAndName = getIpAndName();
            channel = ManagedChannelBuilder.forAddress(ipAndName.getHost(), ipAndName.getPort())
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext().build();
            log.info("DemoClient InitializingBean over");
        }
    }

    protected void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private ServiceInstance getIpAndName(){
        List<ServiceInstance> results=discoveryClient.getInstances(appName);
//        if(CollectionUtils.isEmpty(results)){
//            return null;
//        }
        ServiceInstance serviceInstance=loadBalancerClient.choose(appName);
        System.out.println("DemoClientProxyBase.getIpAndName:"+ serviceInstance.toString());
        return serviceInstance;
    }
}
