package com.consul.register;

import com.consul.RpcURL;

public interface RegistryService {
    void register(RpcURL url);
    void unregister(RpcURL url);
}
