package com.fullmoon.study.injection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterInjectionTest {

	public static void main(String[] args) {
		
		// 指定 Configuration Metadata ( 配置元数据 )
		String configLocations = "classpath:injection/setter-injection.xml" ;
		
		// 创建 Spring IoC 容器
		AbstractApplicationContext context = new ClassPathXmlApplicationContext( configLocations);
		
		// Ready for use ( 此时可以从 IoC 容器中获取指定名称的 Bean 实例了 )
		Person p = context.getBean( "person" , Person.class );
		
		// 从 容器中获取到的 Bean 实例中 获取属性的值
		System.out.println( p.getId() + " , " + p.getName() );
		System.out.println( p.getBirthdate() );
		
		// 关闭 Spring IoC 容器
		context.close();
		
	}

}
