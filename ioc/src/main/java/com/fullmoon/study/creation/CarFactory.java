package com.fullmoon.study.creation;

public class CarFactory {
	
	private String name ;
	
	public Car create(){
		System.out.println( "CarFactory # create" );
		Car c = new Car();
		c.setBrand( name );
		return c ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
