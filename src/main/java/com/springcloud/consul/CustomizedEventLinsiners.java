package com.springcloud.consul;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.consul.ConditionalOnConsulEnabled;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistrationAutoConfiguration;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = {"classpath:consul.properties"})
@ConditionalOnConsulEnabled
@ConditionalOnMissingBean(type= "org.springframework.cloud.consul.discovery.ConsulLifecycle")
@AutoConfigureAfter(ConsulAutoServiceRegistrationAutoConfiguration.class)
public class CustomizedEventLinsiners implements ApplicationContextAware {
    @Value("${application.name}")
    private String appName;

    @Value("${application.rpc.server.port}")
    private String port;

    @Value("${application.hostip}")
    private String ip;

    @Autowired(required=false)
    private ConsulAutoServiceRegistration consulAutoServiceRegistration;

    @Autowired
    private ConsulRegistration consulRegistration;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("register ip:port "+port+":"+port);
        consulRegistration.getService().setPort(Integer.valueOf(port));
        consulRegistration.getService().setAddress(ip);
        consulRegistration.getService().setId(appName + ":" + ip + ":" + port);
        consulRegistration.getService().setName(appName);
        consulRegistration.getService().setTags(Arrays.asList("V3"));
        consulAutoServiceRegistration.start();
    }
}