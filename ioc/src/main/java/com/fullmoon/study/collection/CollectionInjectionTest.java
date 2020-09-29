package com.fullmoon.study.collection;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollectionInjectionTest {

	public static void main(String[] args) {
		
		String configLocations = "classpath:collection/collection-injection.xml" ;
		
		AbstractApplicationContext container = new ClassPathXmlApplicationContext(configLocations);
		
		Human h = container.getBean( "zengsan" , Human.class );
		
		System.out.println( h.getId() + " , " + h.getName() );
		
		Set<String> set = h.getFavorite();
		if( set != null && set.size() > 0 ) {
			System.out.println( set.getClass() );
			
			System.out.println( "爱好:" );
			for( String f : set ){
				System.out.println( "\t" + f );			
			}
		}
		
		System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
		
		List<Date> list = h.getLuckDay();
		if( list != null && list.size() > 0 ) {
			System.out.println( list.getClass() );
			
			System.out.println( "幸运日期:" );
			for( Date d : list ){
				System.out.println( "\t" + d );			
			}
		}
		
		System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
		
		Map<String,String> map = h.getAddress();
		
		
		if( map != null && map.size() > 0 ){
			System.out.println( map.getClass() );
			System.out.println( "地址:" );
			for( Map.Entry<String, String> entry : map.entrySet() ){
				System.out.println( "\t" + entry.getKey() + " : " + entry.getValue() );
			}
		}
		
		System.out.println( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
		
		Properties props = h.getInfos();
		
		
		if( props != null && props.size() > 0 ){
			System.out.println( "不为人知的信息:" );
			Set<Object> keys = props.keySet();
			for( Object key : keys ){
				System.out.println( "\t" + key + " : " + props.get( key ) );
			}
		}
		
		container.close();
		
	}

}
