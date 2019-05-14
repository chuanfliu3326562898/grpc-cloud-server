package com.client;

import com.aconfig.ServerDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
@ConditionalOnClass({ServerDto.class})
public class DemoClientProxyBase {
    @Autowired
    protected ServerDto serverDto;
    protected ManagedChannel channel;

    public static DemoClientProxyBase channelPointer;
    public DemoClientProxyBase(){
        System.out.println("DemoClientProxyBase inited");
    }

    @PostConstruct
    public void init(){
        System.out.println("DemoClientProxyBase postInited");
        start();
        channelPointer=this;
    }

    protected void start() {
        log.info("DemoClient InitializingBean started");
        channel = ManagedChannelBuilder.forAddress(serverDto.getIp(), Integer.parseInt(serverDto.getPort()))
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext().build();
        log.info("DemoClient InitializingBean over");
    }

    protected void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


}
