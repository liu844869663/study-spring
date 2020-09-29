package com.fullmoon.study.test;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Log4j2
public class ReflectTest {

    public static User initByDefaultConst() throws Exception{

        // 通过类加载获取对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.fullmoon.study.test.User");

        // 获取类的默认构造器对象并通过其实例化User
        Constructor cons = clazz.getDeclaredConstructor((Class[])null);
        User user = (User)cons.newInstance();

        // 通过反射方法设置属性
        Method setId = clazz.getMethod("setId", Integer.class);
        setId.invoke(user, 1);
        Method setName = clazz.getMethod("setName", String.class);
        setName.invoke(user, "刘景平");
        Method setAge = clazz.getMethod("setAge", Integer.class);
        setAge.invoke(user, 21);
        Method setPassword = clazz.getMethod("setPassword", String.class);
        setPassword.invoke(user, "123456");

        return user;
    }

    public static void privateReflect() throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.fullmoon.study.test.User");

        User user = (User)clazz.newInstance();

        Field idField = clazz.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, 2);

        log.info(user.toString());
    }

    public static void main(String[] args){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        log.info("current load:{}", loader);
        log.info("parent load:{}", loader.getParent());
        log.info("grandparent load:{}", loader.getParent().getParent());
        try {
            User user = initByDefaultConst();
            log.info("user:{}", user.toString());
            privateReflect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
