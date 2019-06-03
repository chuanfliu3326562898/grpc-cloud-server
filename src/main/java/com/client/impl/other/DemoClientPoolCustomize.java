package com.client;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
 * @Date: 2019-06-02
 */
@Component
@DependsOn("demoClient")
@ComponentScan(lazyInit = true)
public class DemoClientPoolCustomize<T> {
//    Map<>
//    // 取得对象池工厂实例
//    public synchronized GenericObjectPool<DemoClient> getInstance() {
//        if (pool == null) {
//            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//            poolConfig.setMaxIdle(10);
//            poolConfig.setMaxTotal(10);
//            poolConfig.setMinIdle(10);
//            poolConfig.setLifo(false);
//            pool = new GenericObjectPool<DemoClient>(this, poolConfig);
//        }
//        return pool;
//    }
}
