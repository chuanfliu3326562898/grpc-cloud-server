package com.consul.register;

import com.consul.AbstractConsulService;
import com.consul.RpcURL;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegCheck;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.option.QueryOptions;
import org.springframework.stereotype.Service;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
 * @Date: 2019-05-29
 */
@Service
public class ConsulRegistryService  extends AbstractConsulService implements RegistryService {
    private final static int CONSUL_CONNECT_PERIOD=1*1000;

    @Override
    public void register(RpcURL url) {
        Consul consul = this.buildConsul(url.getRegistryHost(),url.getRegistryPort());
        AgentClient agent = consul.agentClient();

        ImmutableRegCheck
            check = ImmutableRegCheck.builder().tcp(url.getHost()+":"+url.getPort()).interval(CONSUL_HEALTH_INTERVAL).build();
        ImmutableRegistration.Builder builder = ImmutableRegistration.builder();
        builder.id(CONSUL_ID).name(CONSUL_NAME).addTags(CONSUL_TAGS)
            .address(url.getHost()).port(url.getPort()).addChecks(check);


        ImmutableRegistration.Builder builder3 = ImmutableRegistration.builder();
        builder3.id("consul_node_ljf2_id").name(CONSUL_NAME).addTags(CONSUL_TAGS)
            .address("127.0.0.3").port(url.getPort()).addChecks(check);
        agent.register(builder3.build());

        System.out.println(agent.getServices());
        System.out.println(agent.getMembers());
        try {
            System.out.println(agent.getService("consul_node_ljf_id", QueryOptions.BLANK));
        } catch (Exception e) {
            System.out.println("error" + e.toString());
        }
        agent.register(builder.build());
    }

    @Override
    public void unregister(RpcURL url) {

    }


    public static void main(String[] args) throws Exception{
        ConsulRegistryService consulRegistryService = new ConsulRegistryService();
        consulRegistryService.register(new RpcURL());
        Thread.sleep(4000);
    }

}
