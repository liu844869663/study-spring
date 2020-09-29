package com.fullmoon.study.relationships;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InhreitRelationship {

	public static void main(String[] args) {
			
		String configLocations = "classpath:relationships/inherit.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		Panda p = container.getBean( "child" , Panda.class );
		
		System.out.println( p.getId() );
		System.out.println( p.getSurname() );
		System.out.println( p.getName() );
		
		container.close();

	}

}
