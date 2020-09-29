package com.fullmoon.study.service.impl;

import com.fullmoon.study.service.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}
