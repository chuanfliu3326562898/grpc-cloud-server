package com.springcloud.consul;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 *
 * @Auther: Yummyxl
 * Company: 跟谁学<p>
 * @Date: 2019/4/28 15:04
 */
public class MyConsulServiceRegistry extends ConsulServiceRegistry {

    public MyConsulServiceRegistry(ConsulClient client, ConsulDiscoveryProperties properties, TtlScheduler ttlScheduler, HeartbeatProperties heartbeatProperties) {
        super(client, properties, ttlScheduler, heartbeatProperties);
    }

    @Override
    public void register(ConsulRegistration reg) {
        System.out.println("MyConsulServiceRegistry:"+reg.getService());
        reg.getService().setId(reg.getService().getName() + "-" + reg.getService().getAddress());
        super.register(reg);
    }
}
