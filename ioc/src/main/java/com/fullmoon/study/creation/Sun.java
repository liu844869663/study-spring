package com.fullmoon.study.creation;

public class Sun {
	
	private static final Sun SUN = new Sun();
	
	private Sun(){}
	
	public static Sun getInstance(){
		System.out.println( "Sun.getInstance" );
		return SUN ;
	}

}
