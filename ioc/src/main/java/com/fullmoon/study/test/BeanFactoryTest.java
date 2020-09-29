package com.fullmoon.study.test;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Log4j2
public class BeanFactoryTest {
    public static void main(String[] args) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:test/user.xml");
        log.info("xml url:{}", res.getURL());

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        // XmlBeanDefinitionReader通过Resource装载Spring配置信息并启动IoC容器
        reader.loadBeanDefinitions(res);
        log.info("init factory");
        /*
         * 初始化完成后，可通过IoC容器中获取相应的Bean
         * 通过BeanFactory启动IoC容器时不会初始化配置文件中定义的Bean，初始化动作发生在第一次调用
         */

        User user = factory.getBean("user", User.class);
        log.info("user bean is ready for use");
        log.info("user:{}", user.toString());
    }
}
