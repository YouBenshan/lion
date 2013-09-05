package com.cj.utils;

import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesUtil {
	public static Properties read(String propertiesFilePath) {
		Properties propeties = new Properties();
		try {
			propeties.load(PropertiesUtil.class
					.getResourceAsStream(propertiesFilePath));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return propeties;
	}
}
