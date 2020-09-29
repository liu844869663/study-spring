package com.fullmoon.study.aop.first;

import lombok.extern.log4j.Log4j2;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

@Log4j2
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String clientName = (String)objects[0];
        log.info("How are you! Mr.{}.", clientName);
    }
}
