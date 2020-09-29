package com.fullmoon.study.creation;

public class Sinaean {

	private Integer id;
	private String name; 
	
	public Sinaean() {
		super();
		System.out.println( "Sinaean无参构造执行" );
	}

	public Sinaean(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		System.out.println( "Sinaean有参构造执行" );
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
