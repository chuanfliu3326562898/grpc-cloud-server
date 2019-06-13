package com.consul.discover;

import com.alibaba.fastjson.JSON;
import com.consul.AbstractConsulService;
import com.consul.RpcURL;
import com.google.common.collect.Lists;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.ConsulResponse;
import com.orbitz.consul.model.health.ImmutableServiceHealth;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * <p>
 * @Date: 2019-05-29
 */
@Service
public class ConsulDiscoveryService extends AbstractConsulService implements DiscoveryService {

    @Override
    public List<RpcURL> getUrls(String registryHost, int registryPort) {
        List<RpcURL> urls = Lists.newArrayList();
        Consul consul = this.buildConsul(registryHost, registryPort);
        HealthClient client = consul.healthClient();
        String name= CONSUL_NAME;
        ConsulResponse object = client.getAllServiceInstances(name);
        System.out.println(JSON.toJSONString(object));
        List<ImmutableServiceHealth> serviceHealths = (List<ImmutableServiceHealth>)object.getResponse();
        for (ImmutableServiceHealth serviceHealth : serviceHealths) {
            RpcURL url = new RpcURL();
            url.setHost(serviceHealth.getService().getAddress());
            url.setPort(serviceHealth.getService().getPort());
            urls.add(url);
        }
        return urls;
    }

    public static void main(String[] args) {
        ConsulDiscoveryService consulDiscoveryService = new ConsulDiscoveryService();
        RpcURL rpcURL=new RpcURL();
        List<RpcURL>  rpcURLS=consulDiscoveryService.getUrls(rpcURL.getRegistryHost(),rpcURL.getRegistryPort());
        System.out.println(Arrays.toString(rpcURLS.toArray()));
    }
}
