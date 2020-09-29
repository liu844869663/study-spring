package com.fullmoon.study.relationships;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependOnRelationship {

	public static void main(String[] args) {
			
		String configLocations = "classpath:relationships/depends-on.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		container.close();

	}

}
