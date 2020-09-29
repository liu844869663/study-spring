package com.fullmoon.study.creation;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCreationByConstructor {

	public static void main(String[] args) {
		
		String configLocations = "classpath:creation/constructor-bean-creation.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		Sinaean wk = container.getBean( "wukong" , Sinaean.class );
		System.out.println( wk.getId() + " , " + wk.getName() );
		
		Sinaean wn = container.getBean( "wuneng" , Sinaean.class );
		System.out.println( wn.getId() + " , " + wn.getName() );
		
		
		container.close();
		
	}

}
