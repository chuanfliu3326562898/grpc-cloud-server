package com.consul.discover;

import com.consul.RpcURL;

import java.util.List;

public interface DiscoveryService {

    List<RpcURL> getUrls(String registryHost, int registryPort);
}