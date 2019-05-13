package com.controller;

import com.client.DemoClientPoolFactory;
import com.aconfig.ServerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用模块名称<p>
 * 代码描述<获取所有阶段返回dto>
 *
 * @Author: liujiangfeng
 * Company: 跟谁学<p>
 * @Date: 2019-05-08
 */
@RestController
@RequestMapping("/hello")
public class HelloServerController {
    @Autowired
    ServerDto serverDto;
    @Autowired
    DemoClientPoolFactory demoClientPoolFactory;

    @GetMapping("/rpc") public String rpc() throws Exception {
        String result=DemoClientPoolFactory.demoClientPoolFactory.borrowObject().hello("test");
        return serverDto == null ? serverDto.getIp() : result;
    }
    @GetMapping("/test")
    public String test() throws Exception {
        return "ok";
    }

}