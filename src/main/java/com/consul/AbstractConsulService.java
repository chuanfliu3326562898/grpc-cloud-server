package com.consul;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractConsulService {
    private static final Logger logger = LoggerFactory.getLogger(AbstractConsulService.class);

    protected final static String CONSUL_NAME="consul_node_ljf";
    protected final static String CONSUL_ID="consul_node_ljf_id";
    protected final static String CONSUL_TAGS="v3";
    protected final static String CONSUL_HEALTH_INTERVAL="2s";

    protected Consul buildConsul(String registryHost, int registryPort){
        return Consul.builder().withHostAndPort(HostAndPort.fromString(registryHost+":"+registryPort)).build();
    }
}
