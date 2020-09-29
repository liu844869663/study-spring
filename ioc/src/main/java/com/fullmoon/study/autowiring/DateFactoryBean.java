package com.fullmoon.study.autowiring;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.FactoryBean;

public class DateFactoryBean implements FactoryBean<Date>{
	
	private int year ;
	private int month ;
	private int date ;
	private int hour ;
	private int minute ;
	private int second ;
	
	private Calendar calendar ;
	
	public DateFactoryBean() {
		calendar = Calendar.getInstance();
		//calendar.set( calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONTH ), calendar.get( Calendar.DATE ), 0, 0, 0);
		//calendar.set( Calendar.HOUR,  0 );
		//calendar.set( Calendar.HOUR_OF_DAY,  0 );
		//calendar.set( Calendar.MINUTE,  0 );
		//calendar.set( Calendar.SECOND,  0 );
		calendar.set( Calendar.MILLISECOND,  0 );
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}


	@Override
	public Date getObject() throws Exception {

		calendar.set(Calendar.YEAR, year );
		calendar.set(Calendar.MONTH, month - 1 );
		calendar.set( Calendar.DATE,  date );
		calendar.set( Calendar.HOUR , hour );
		calendar.set( Calendar.HOUR_OF_DAY,  hour );
		calendar.set( Calendar.MINUTE , minute );
		calendar.set( Calendar.HOUR , second );

		Date date = calendar.getTime();

		return date;
	}

	@Override
	public Class<?> getObjectType() {
		return Date.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
