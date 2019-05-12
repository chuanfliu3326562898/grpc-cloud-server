package com.config;

import com.grpc.server.MyDemoServerImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
public class DemoServer implements InitializingBean {
    private Server server;
    @Autowired
    private ServerDto serverDto;
    @Autowired
    private MyDemoServerImpl myDemoServerImpl;

    @Override
    public void afterPropertiesSet() throws Exception {
        start(serverDto, myDemoServerImpl);
        startDaemonAwaitThread();
    }

    private void start(ServerDto serverDto, MyDemoServerImpl myDemoServerImpl) throws IOException {
        /* The port on which the com.grpc.server should run */
        int port = Integer.parseInt(serverDto.getPort());
        server = ServerBuilder.forPort(port).addService(myDemoServerImpl).build().start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC com.grpc.server since JVM is shutting down");
                DemoServer.this.stop();
                System.err.println("*** com.grpc.server shut down");
            }
        });
        System.out.println("server:" + serverDto.getName() + " rpc port:" + port + " started");
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    //replace blockUntilShutdown
    private void startDaemonAwaitThread() {
        Thread awaitThread = new Thread(() -> {
            try {
                this.server.awaitTermination();
            } catch (InterruptedException e) {
                log.warn("gRPC server stopped." + e.getMessage());
            }
        });
        awaitThread.setDaemon(false);
        awaitThread.start();
    }

}