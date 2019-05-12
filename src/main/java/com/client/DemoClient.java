package com.client;

import com.config.ServerDto;
import com.grpc.dto.DemoResponse;
import com.grpc.dto.DemoRquest;
import com.grpc.dto.DemoServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
 * @Date: 2019-05-06
 */
@Component
@Slf4j
public class DemoClient  {
    @Autowired
    private ServerDto serverDto;
    private ManagedChannel channel;
    private DemoServerGrpc.DemoServerBlockingStub blockingStub;
    private ExecutorService clientRpcPool;

    public DemoClient(){
        start();
        getThreadPool();
    }

    private ExecutorService getThreadPool() {
        if (clientRpcPool == null) {
            clientRpcPool = Executors.newFixedThreadPool(50);
        }
        return clientRpcPool;
    }

    public void start() {
        log.info("DemoClient InitializingBean started");
        channel = ManagedChannelBuilder.forAddress(serverDto.getIp(), Integer.parseInt(serverDto.getPort()))
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext().build();
        blockingStub = DemoServerGrpc.newBlockingStub(channel);
        log.info("DemoClient InitializingBean over");
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /** Say hello to server. */
    public String hello(String name) throws Exception,InterruptedException{
            log.info("rpc hello method call started");
            DemoRquest request = DemoRquest.newBuilder().setCode(1).setMsg(name).build();
            DemoResponse response;
            try {
                response = blockingStub.hello(request);
                return Thread.currentThread().getName()+response.getCode()+":"+response.getMsg();
            } catch (StatusRuntimeException e) {
                return "fail";
            }

//        clientRpcPool.execute(()->{
//            log.info("rpc hello method call started");
//            DemoRquest request = DemoRquest.newBuilder().setCode(1).setMsg(name).build();
//            DemoResponse response;
//            try {
//                response = blockingStub.hello(request);
//                return Thread.currentThread().getName()+response.getCode()+":"+response.getMsg();
//            } catch (StatusRuntimeException e) {
//                return "fail";
//            }
//        });
    }

    public static void main(String[] args) throws Exception{
        new DemoClient().hello("test");
    }
}
