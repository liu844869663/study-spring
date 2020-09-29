package com.fullmoon.study;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.fullmoon.study.service.Robot;
import lombok.extern.log4j.Log4j2;

import java.util.ServiceLoader;

@Log4j2
public class TestSPIMain {
    public static void main(String[] args){
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        log.info("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
        log.info("-------------------------");
        log.info("Dubbo SPI");
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
