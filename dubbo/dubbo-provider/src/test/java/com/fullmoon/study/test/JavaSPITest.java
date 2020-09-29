package com.fullmoon.study.test;

import com.fullmoon.study.service.Robot;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ServiceLoader;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/spring-mybatis.xml",
        "classpath:META-INF/spring/spring-mvc.xml","classpath:META-INF/spring/dubbo-provider.xml"})
public class JavaSPITest {

    @Test
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        log.info("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}
