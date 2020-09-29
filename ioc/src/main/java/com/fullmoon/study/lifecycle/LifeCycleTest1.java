package com.fullmoon.study.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleTest1 {

	public static void main(String[] args) {
		
		String configLocations = "classpath:lifecycle/lifecycle.xml" ;
		
		System.out.println( "~~~first~~~~~~~~~~~~~~~~~~~~" );
		
		// AbstractApplicationContext 实现了 ApplicationContext
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		System.out.println( "~~~second~~~~~~~~~~~~~~~~~~~~" );
		
		Dog d = container.getBean( "dog" , Dog.class );
		System.out.println( d.getId() + " : " + d.getName() );
		
		System.out.println( "~~~thrid~~~~~~~~~~~~~~~~~~~~" );
		
		d = container.getBean( "dog" , Dog.class );
		System.out.println( d.getId() + " : " + d.getName() );
		
		System.out.println( "~~~forth~~~~~~~~~~~~~~~~~~~~" );
		
		container.close();
	}

}
