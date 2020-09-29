package com.fullmoon.study.creation;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCreationByStaticFactory {

	public static void main(String[] args) {
		
		String configLocations = "classpath:creation/static-factory-bean-creation.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		Train t = container.getBean( "train" , Train.class );
		System.out.println( t );
		
		t = container.getBean( "train" , Train.class );
		System.out.println( t );
		
		Sun s = container.getBean("sun", Sun.class);
		System.out.println(s);

		s = container.getBean("sun", Sun.class);
		System.out.println(s);

		s = container.getBean("sun", Sun.class);
		System.out.println(s);
		
		
		container.close();
		
	}

}
