package com.fullmoon.study.autowiring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiringByType {

	public static void main(String[] args) {
		
		String configLocations = "classpath:autowiring/byType-autowiring.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		Hero h = container.getBean( "yangGuo"  , Hero.class ) ;
		System.out.println( h.getId() + " , " + h.getName() + " , " + h.getGender() );
		System.out.println( h.getBirthdate() );
		
		Hero f = h.getFriend();
		
		if( f != null ) {
			System.out.println( f.getId() + " , " + f.getName() + " , " + f.getGender() );
			System.out.println( f.getBirthdate() );
		} else {
			System.out.println( h.getName() + "没有朋友" );
		}
		
		container.close();
		
	}

}
