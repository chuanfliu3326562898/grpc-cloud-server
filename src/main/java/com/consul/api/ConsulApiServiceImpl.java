package com.consul.api;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.health.model.HealthService;
import com.ecwid.consul.v1.kv.model.GetValue;

import java.util.Arrays;
import java.util.List;

public class ConsulApiServiceImpl {
 
    private ConsulClient client = null;
 
    public ConsulApiServiceImpl() {
        client = new ConsulClient("127.0.0.1", 8500);
    }
    public void registerService(String serviceName, String serviceId) {
        // register new service
        NewService newService = new NewService();
        newService.setId(serviceId);
        newService.setName(serviceName);
        newService.setTags(Arrays.asList("EU-West", "EU-East"));
        newService.setPort(8080);
 
        NewService.Check serviceCheck = new NewService.Check();
        serviceCheck.setHttp("http://127.0.0.1:8080/health");
        serviceCheck.setInterval("10s");
        newService.setCheck(serviceCheck);
        client.agentServiceRegister(newService);
    }

    public static void main(String[] args) {
        ConsulApiServiceImpl consulApiService=new ConsulApiServiceImpl();
        consulApiService.registerService("service-api","service-api-id1");
        consulApiService.registerService("service-api","service-api-id2");
        consulApiService.registerService("service-api2","service-api-id3");

        System.out.println("service-api:"+consulApiService.findHealthyService("service-api").size());
        System.out.println("service-api:"+consulApiService.findHealthyService("service-api2").size());
        System.out.println(consulApiService.getKV("service-api-id1"));
        System.out.println(consulApiService.getKV("service-api"));
    }

    public List<HealthService> findHealthyService(String serviceName) {
        Response<List<HealthService>> healthyServices = client.getHealthServices(serviceName, true, QueryParams.DEFAULT);
        return healthyServices.getValue();
    }

    public void storeKV(String key, String value) {
        Response<Boolean> booleanResponse = client.setKVValue(key, value);
    }

    public String getKV(String key) {
        Response<GetValue> getValueResponse = client.getKVValue(key);
        //return getValueResponse.getValue().getValue();
        return getValueResponse.getValue().getDecodedValue();
    }

    public List<String> findRaftPeers() {
        Response<List<String>> listResponse = client.getStatusPeers();
        return listResponse.getValue();
    }

    public String findRaftLeader() {
        Response<String> stringResponse = client.getStatusLeader();
        return stringResponse.getValue();
    }
}
