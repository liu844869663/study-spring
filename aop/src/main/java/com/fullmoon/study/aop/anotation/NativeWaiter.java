package com.fullmoon.study.aop.anotation;

import com.fullmoon.study.aop.first.Waiter;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;

@Log4j2
public class NativeWaiter implements Waiter {
    public void greetTo(String name) {
        log.info("greet to {} ..." , name);
    }

    public void serveTo(String name) {
        log.info("serving {} ..." , name);
    }
}
