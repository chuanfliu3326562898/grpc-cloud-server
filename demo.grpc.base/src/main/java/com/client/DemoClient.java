package com.client;

import com.server.dto.ServerDto;
import io.grpc.*;
import io.grpc.internal.DnsNameResolverProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.rmi.server.RemoteServer;
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
@Data
@Component
@Slf4j
public class DemoClient {
    private String serviceName;

    private final GrpcProperties grpcProperties;

    @Autowired
    private ServerDto serverDto;
    private ManagedChannel channel;
   // private DemoServerGrpc.DemoServerBlockingStub blockingStub;

    public void start() {
        log.info("DemoClient InitializingBean started");
        channel = ManagedChannelBuilder.forAddress(serverDto.getIp(), Integer.parseInt(serverDto.getPort()))
            .usePlaintext().build();
        // blockingStub = DemoServerGrpc.newBlockingStub(channel);
        log.info("DemoClient InitializingBean over");
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * 初始化
     */
    public void init(){
        List<RemoteServer> remoteServers = grpcProperties.getRemoteServers();
        if (!CollectionUtils.isEmpty(remoteServers)) {
            for (RemoteServer server : remoteServers) {
                ManagedChannel channel = ManagedChannelBuilder.forAddress(server.getHost(), server.getPort())
                    .defaultLoadBalancingPolicy("round_robin")
                    .nameResolverFactory(DnsNameResolverProvider.asFactory())
                    .idleTimeout(30, TimeUnit.SECONDS)
                    .usePlaintext().build();
                if (clientInterceptor != null){
                    Channel newChannel = ClientInterceptors.intercept(channel, clientInterceptor);
                    serverMap.put(server.getServer(), new ServerContext(newChannel, serializeService));
                }else {
                    Class clazz = grpcProperties.getClientInterceptor();
                    if (clazz == null) {
                        serverMap.put(server.getServer(), new ServerContext(channel, serializeService));
                    }else {
                        try {
                            ClientInterceptor interceptor = (ClientInterceptor) clazz.newInstance();
                            Channel newChannel = ClientInterceptors.intercept(channel, interceptor);
                            serverMap.put(server.getServer(), new ServerContext(newChannel, serializeService));
                        } catch (InstantiationException | IllegalAccessException e) {
                            log.warn("ClientInterceptor cannot use, ignoring...");
                            serverMap.put(server.getServer(), new ServerContext(channel, serializeService));
                        }
                    }
                }
            }
        }
    }

    /**
     * 连接远程服务
     */
    public static ServerContext connect(String serverName) {
        return serverMap.get(serverName);
    }
}
