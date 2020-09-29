package com.fullmoon.study.creation;

import org.springframework.beans.factory.FactoryBean;

public class BusFactoryBean implements FactoryBean<Bus>{
	
	private String brand ;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public Bus getObject() throws Exception {
		Bus bus = new Bus();
		bus.setBrand( brand );
		return bus;
	}

	@Override
	public Class<?> getObjectType() {
		return Bus.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
