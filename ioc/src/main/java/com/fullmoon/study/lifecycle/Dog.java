package com.fullmoon.study.lifecycle;

public class Dog {

	private Integer id;
	private String name;
	
	public Dog() {
		super();
		System.out.println( "constructor" );
	}
	
	public void init(){
		System.out.println( "init" );
	}
	
	public void destory(){
		System.out.println( "destory" );
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
