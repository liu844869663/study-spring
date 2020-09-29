package com.fullmoon.study.creation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCreationByFactoryBean {

	public static void main(String[] args) {
		
		String configLocations = "classpath:creation/factory-bean-bean-creation.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		Date d = container.getBean( "d" , Date.class );
		System.out.println( d.getClass() );
		
		Object o = container.getBean( "b" );
		System.out.println( o.getClass() );
		
		Bus b = container.getBean( "b" , Bus.class );
		System.out.println( b.getBrand() );
		
		System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~" );
		
		Date date = container.getBean( "birthdate" , Date.class );
		
		System.out.println( date );
		
		DateFormat df =  new SimpleDateFormat( "yyyy年MM月dd日 HH:mm:ss.SSS" );
		
		System.out.println( df.format( date ) );
		
		container.close();
		
	}

}
