package com.fullmoon.study.relationships;

public class Panda {

	private Integer id; 
	private String surname; // 姓
	private String name; // 名
	
	public Panda() {
		super();
		System.out.println( "Panda Constructor" );
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
