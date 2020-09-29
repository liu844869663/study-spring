package com.fullmoon.study.rpc;

import com.fullmoon.study.service.HelloService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        HelloService helloService = RpcFramework.refer(HelloService.class, "127.0.0.1", 2233);
        log.info(helloService.hello("123"));
    }
}
