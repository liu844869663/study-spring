package com.fullmoon.study.lifecycle;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@SuppressWarnings("deprecation")
public class LifeCycleTest2 {

	public static void main(String[] args) {
		
		
		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver() ;
		
		String location = "classpath:lifecycle/lifecycle.xml" ;
		
		Resource resource = resolver.getResource(location) ;
		
		System.out.println( "~~~first~~~~~~~~~~~~~~~~~~~~" );
		
		BeanFactory container = new XmlBeanFactory( resource ) ;
		
		System.out.println( "~~~second~~~~~~~~~~~~~~~~~~~~" );
		
		Dog d = container.getBean( "dog" , Dog.class );
		System.out.println( d.getId() + " : " + d.getName() );
		
		System.out.println( "~~~thrid~~~~~~~~~~~~~~~~~~~~" );
		
		d = container.getBean( "dog" , Dog.class );
		System.out.println( d.getId() + " : " + d.getName() );
		
		System.out.println( "~~~forth~~~~~~~~~~~~~~~~~~~~" );
		
	}

}
