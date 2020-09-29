package com.fullmoon.study.creation;

public class Train {
	
	private Train(){
	}
	
	public static Train create(){
		System.out.println( "Train.create" );
		Train t = new Train();
		return t ;
	}

}
