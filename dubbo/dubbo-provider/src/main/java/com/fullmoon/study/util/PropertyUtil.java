package com.fullmoon.study.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Log4j2
public class PropertyUtil {
	static ResourceBundle resource;

	static {
		try {
			resource = ResourceBundle.getBundle("test");
		} catch (MissingResourceException e1) {
			log.error(e1.getMessage(), e1);
			resource = ResourceBundle.getBundle("META-INF/test");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static String getProperty(String key, String defaultValue) {
		try {
			return resource.getString(key) == null ? defaultValue : resource.getString(key);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String getProperty(String key) {
		return resource.getString(key);
	}

	public static Integer getPropertyInt(String key) {
		return StringUtils.isEmpty(resource.getString(key)) ? null : Integer.parseInt(resource.getString(key));
	}

	public static void main(String[] args) {

	}
}