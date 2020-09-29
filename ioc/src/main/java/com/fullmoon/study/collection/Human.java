package com.fullmoon.study.collection;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Human {

	private Integer id;
	private String name;

	private Set<String> favorite;

	private List<Date> luckDay;

	private Map<String, String> address;
	
	private Properties infos ;

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

	public Set<String> getFavorite() {
		return favorite;
	}

	public void setFavorite(Set<String> favorite) {
		this.favorite = favorite;
	}

	public List<Date> getLuckDay() {
		return luckDay;
	}

	public void setLuckDay(List<Date> luckDay) {
		this.luckDay = luckDay;
	}

	public Map<String, String> getAddress() {
		return address;
	}

	public void setAddress(Map<String, String> address) {
		this.address = address;
	}

	public Properties getInfos() {
		return infos;
	}

	public void setInfos(Properties infos) {
		this.infos = infos;
	}

}
