package com.fullmoon.study.creation;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCreationByInstanceFactory {

	public static void main(String[] args) {
		
		String configLocations = "classpath:creation/instance-factory-bean-creation.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		Car c = container.getBean( "car" , Car.class );
		System.out.println( c );
		System.out.println( c.getBrand() );
		
		c = container.getBean( "car" , Car.class );
		System.out.println( c );
		System.out.println( c.getBrand() );
		
		container.close();
		
	}

}
