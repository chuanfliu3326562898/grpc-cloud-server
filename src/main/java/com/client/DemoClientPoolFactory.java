package com.client;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("demoClient")
public class DemoClientPoolFactory extends BasePooledObjectFactory<DemoClient> {

    public static DemoClientPoolFactory demoClientPoolFactory;

    public DemoClientPoolFactory(){
        System.out.println("DemoClientPoolFactory inited");
    }

    @PostConstruct
    public void init() {
        System.out.println("DemoClientPoolFactory postInited");
        getInstance();
        demoClientPoolFactory = this;
    }

    private GenericObjectPool<DemoClient> pool = null;

    // 取得对象池工厂实例
    public synchronized GenericObjectPool<DemoClient> getInstance() {
        if (pool == null) {
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
            poolConfig.setMaxIdle(10);
            poolConfig.setMaxTotal(10);
            poolConfig.setMinIdle(10);
            poolConfig.setLifo(false);
            pool = new GenericObjectPool<DemoClient>(this, poolConfig);
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