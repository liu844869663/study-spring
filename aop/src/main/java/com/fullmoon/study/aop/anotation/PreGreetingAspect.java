package com.fullmoon.study.aop.anotation;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Log4j2
public class PreGreetingAspect {

    @Before("execution(* greetTo(..))")
    public void beforeGreeting(){
        log.info("How are you!");
    }
}
