package com.fullmoon.study.relationships;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReferenceRelationship {

	public static void main(String[] args) {
			
		String configLocations = "classpath:relationships/reference.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		Names p = container.getBean( "names" , Names.class );
		
		System.out.println( p.getName1().getClass() );
		System.out.println( p.getName2() );
		System.out.println( p.getName3() );
		
		container.close();

	}

}
