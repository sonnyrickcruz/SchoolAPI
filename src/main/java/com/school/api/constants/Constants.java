package com.school.api.constants;

import org.springframework.context.annotation.ComponentScan;


@ComponentScan
public class Constants{
	public static final String SUCCESS = Config.getProperty("response.success");
	public static final String FAIL = Config.getProperty("response.fail");
	public static final String DATE_FORMAT = Config.getProperty("date.format");
	private Constants(){}
}
