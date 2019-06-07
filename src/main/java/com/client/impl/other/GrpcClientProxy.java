package com.client.impl.other;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.AbstractStub;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
 * @Date: 2019-06-02
 */
@Component
@Data
@Slf4j
public class GrpcClientProxy {
    protected ManagedChannel channel;
    @Autowired
    private ConsulDiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    protected AbstractStub blockingStub;
    @Value("${application.name}")
    private String appName;

    public String proxyOneMethod(Class<? extends AbstractStub> blockingStub,String methodName,Object paramRequest){
        try{
            Constructor cla = blockingStub.getDeclaredConstructor(AbstractStub.class);
            this.blockingStub=(AbstractStub)cla.newInstance(channel);
            //jdk
        }catch (Exception e){
            log.error("info error");
        }
        return null;
    }

    public void init() {
        if(channel==null){
            log.info("DemoClient InitializingBean started");
            String[] ipAndName = getIpAndName();
            channel = ManagedChannelBuilder.forAddress(ipAndName[0], Integer.parseInt(ipAndName[1]))
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
