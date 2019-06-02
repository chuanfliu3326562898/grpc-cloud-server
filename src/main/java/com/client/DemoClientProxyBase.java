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
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
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

  //  public static DemoClientProxyBase channelPointer;
    public DemoClientProxyBase(){
        System.out.println("DemoClientProxyBase inited");
    }

    @PostConstruct
    public void init(){
        System.out.println("DemoClientProxyBase postInited");
 //       channelPointer=this;
//        start();
    }

    public void start() {
        if(channel==null){
            log.info("DemoClient InitializingBean started");
            String[] ipAndName = getIpAndName();
            channel = ManagedChannelBuilder.forAddress(ipAndName[0], Integer.parseInt(ipAndName[1]))
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext().build();
            log.info("DemoClient InitializingBean over");
        }
    }

    protected void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private String[] getIpAndName(){
        List<ServiceInstance> results=discoveryClient.getInstances(appName);
        if(CollectionUtils.isEmpty(results)){
            return null;
        }
        String[] ipAndName = {results.get(0).getHost(), String.valueOf(results.get(0).getPort())};
        System.out.println("DemoClientProxyBase.getIpAndName:"+ Arrays.toString(ipAndName));
        return ipAndName;

    }
}
