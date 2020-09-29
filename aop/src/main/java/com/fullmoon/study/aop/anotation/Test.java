package com.fullmoon.study.aop.anotation;

import java.lang.reflect.Method;
import com.fullmoon.study.aop.anotation.NeedTest;
import com.fullmoon.study.aop.first.Waiter;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class Test {

    @NeedTest(value = false)
    public void test(String id){
        System.out.println(id);
    }

    public static void main(String[] args){
        Class clazz = Test.class;

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods){
            NeedTest nt = method.getAnnotation(NeedTest.class);
            if(nt != null){
                if(nt.value()){
                    System.out.println(method.getName() + "()需要进行测试");
                }else{
                    System.out.println(method.getName() + "()不需要测试");
                }

            }
        }

        Waiter target = new NativeWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(target);
        factory.addAspect(PreGreetingAspect.class);
        Waiter proxy = factory.getProxy();

        proxy.greetTo("123");
    }
}
