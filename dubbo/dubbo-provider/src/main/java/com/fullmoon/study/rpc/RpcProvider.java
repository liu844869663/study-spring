package com.fullmoon.study.rpc;

import com.fullmoon.study.service.HelloService;
import com.fullmoon.study.service.impl.HelloServiceImpl;

public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        RpcFramework.export(helloService, 2233);
    }
}
