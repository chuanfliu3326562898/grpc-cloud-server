package com.client;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DemoClientPoolFactory extends BasePooledObjectFactory<DemoClient> {

    public static DemoClientPoolFactory demoClientPoolFactory;

    @PostConstruct
    public void init() {
        demoClientPoolFactory = this;
    }

    private GenericObjectPool<DemoClient> pool = null;

    // 取得对象池工厂实例
    public synchronized GenericObjectPool<DemoClient> getInstance() {
        if (pool == null) {
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxIdle(3);
            poolConfig.setMaxTotal(3);
            poolConfig.setMinIdle(10);
            poolConfig.setLifo(false);
            pool = new GenericObjectPool<DemoClient>(new DemoClientPoolFactory(), poolConfig);
        }
        return pool;
    }

    public DemoClient borrowObject() throws Exception {
        return (DemoClient)getInstance().borrowObject();
    }

    public void returnObject(DemoClient demoClient) throws Exception {
        getInstance().returnObject(demoClient);
    }

    public void close() throws Exception {
        getInstance().close();
    }

    public void clear() throws Exception {
        getInstance().clear();
    }

    @Override
    public DemoClient create() throws Exception {
        return new DemoClient();
    }

    @Override
    public PooledObject<DemoClient> wrap(DemoClient obj) {
        return new DefaultPooledObject<DemoClient>(obj);
    }
}