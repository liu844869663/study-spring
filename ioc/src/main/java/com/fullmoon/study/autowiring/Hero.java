package com.fullmoon.study.autowiring;

import java.util.Date;

public class Hero {

	private Integer id;
	private String name;
	private char gender;
	private Date birthdate;
	
	private Hero friend ;
	
	public Hero(){
		super();
		System.out.println( "Hero Constructor." );
	}

	public Hero(Hero friend) {
		super();
		this.friend = friend;
		System.out.println( "== public Hero(Hero friend) ==" );
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

	public Hero getFriend() {
		return friend;
	}

	public void setFriend(Hero friend) {
		this.friend = friend;
	}
	
	

}
