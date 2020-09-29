package com.fullmoon.study.service.impl;

import com.fullmoon.study.service.Robot;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OptimusPrime implements Robot {
    @Override
    public void sayHello() {
        log.info("Hello, I am Optimus Prime.");
    }
}
