package com.school.api.constants;

import org.springframework.context.annotation.ComponentScan;


@ComponentScan
public class Constants{
	public static final String SUCCESS = Config.getProperty("response.success");
	public static final String FAIL = Config.getProperty("response.fail");
	public static final String INPUT = "INPUT";
	public static final String CREATE_ACTION = "ADD";
	public static final String EDIT_ACTION = "EDIT";
	public static final String READ_ACTION = "READ";
	public static final String DELETE_ACTION = "DELETE";
	public static final String INACTIVE_STATUS = "INACTIVE";
	public static final String ACTIVE_STATUS = "ACTIVE";
	public static final String DATE_FORMAT = Config.getProperty("date.format");
	private Constants(){}
}
