package com.fullmoon.study.aop;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        ForumService target = new ForumServiceImpl();
        PerformanceHandler handler = new PerformanceHandler(target);

        ForumService proxy = (ForumService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);

        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }
}
