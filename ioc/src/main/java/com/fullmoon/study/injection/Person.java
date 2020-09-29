package com.fullmoon.study.injection;

import java.util.Date;

public class Person {

	private Integer id;
	private String name;
	private char gender;
	private Date birthdate;
	private boolean married;
	
	public Person() {
		super();
	}

	public Person(Integer id, String name) {
		super();
		System.out.println(  "Person : " + id + " , " + name );
		this.id = id;
		this.name = name;
	}
	
	public Person(Integer id, String name, char gender, Date birthdate, boolean married) {
		super();
		System.out.println( "Person : " + id + " , " + name );
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthdate = birthdate;
		this.married = married;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		System.out.println( "setter : " + id );
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println( "setter : " + name );
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

}
