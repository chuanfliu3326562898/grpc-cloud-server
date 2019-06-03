package com.client;

import com.biz.HelloService;
import com.grpc.dto.DemoResponse;
import com.grpc.dto.DemoRquest;
import com.grpc.dto.DemoServerGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
 * @Date: 2019-05-06
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@DependsOn("demoClientProxyBase")
public class DemoClient  implements HelloService {
    private static int count=0;

    @Autowired
    private DemoClientProxyBase demoClientProxyBase;

    protected DemoServerGrpc.DemoServerBlockingStub blockingStub;


    public DemoClient(){
        System.out.println("demoClientProxyBase:"+demoClientProxyBase==null+"DemoClient inited"+count++);
    }
    public DemoClient(DemoClientProxyBase demoClientProxyBase){
        this.demoClientProxyBase=demoClientProxyBase;
        System.out.println("demoClientProxyBase:"+demoClientProxyBase==null+"DemoClient inited"+count++);
    }
    @PostConstruct
    public void postConstruct(){
        System.out.println("DemoClient postInited");
        System.out.println("demoClientProxyBase.channel:"+demoClientProxyBase.channel==null);
    }

    @Override
    public String hello(String input) {
        demoClientProxyBase.start();
        blockingStub = DemoServerGrpc.newBlockingStub(demoClientProxyBase.channel);
        log.info("rpc hello method call started");
        DemoRquest request = DemoRquest.newBuilder().setCode(1).setMsg(input).build();
        DemoResponse response;
        try {
            response = blockingStub.hello(request);
            return Thread.currentThread().getName() + response.getCode() + ":" + response.getMsg();
        } catch (Exception e) {
            log.error("rpc register call server fail :", e);
            return "fail";
        } finally {
            try {
                DemoClientPoolFactory.demoClientPoolFactory.returnObject(this);
            } catch (Exception e) {
                log.info("DemoClientPoolFactory.returnObject error ,e:" + e);
            }
        }
    }

    @Override
    public String hello2(String input) {
        // super.blockingStub.hello(request);
        return null;
    }
}
