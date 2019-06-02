package com.springcloud.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.Member;
import com.ecwid.consul.v1.agent.model.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ConsulController {
    @Value("${application.name}")
    private String appName;
    @Autowired
    private ConsulDiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private  ConsulClient consulClient;
    @Autowired
    private  ConsulRegistration consulRegistration;


    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        return discoveryClient.getServices();
    }

    /**
     * 获取所有服务
     */
    @RequestMapping("/service/infos")
    public String getServiceMembers() {
        //String infos=discoveryClient.getInstances(appName);
        String servicename=discoveryClient.getInstances(appName).toString();
        String serviceid=discoveryClient.getInstances("test-grpc-consul:127.0.0.1:50052").toString();
        return servicename+"\n"+serviceid;
    }

    @DeleteMapping("api/deregister")
    public void deregister() {
        String currentInstanceId = consulRegistration.getInstanceId();
        List<Member> members = consulClient.getAgentMembers().getValue();
        for (Member member : members) {
            String address = member.getAddress();
            ConsulClient clearClient = new ConsulClient(address);
            try {
                Map<String, Service> serviceMap = clearClient.getAgentServices().getValue();
                for (Map.Entry<String, Service> entry : serviceMap.entrySet()) {
                    Service service = entry.getValue();
                    String instanceId = service.getId();
                    if (currentInstanceId.equals(instanceId)) {
                        log.warn("在{}客户端上的服务 :{}为无效服务，准备清理...................", address, currentInstanceId);
                        clearClient.agentServiceDeregister(currentInstanceId);
                    }
                }
            } catch (Exception e) {
                log.error("异常信息: {}", e);
            }
        }
    }

}