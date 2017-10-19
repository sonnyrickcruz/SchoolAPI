package com.school.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.school.api.constants.Constants;

public class StringUtils {
	private StringUtils() {
	}
	
	static DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

	/**
	 * Convert string to date based on the format
	 * 
	 * @param dateString
	 * @return date
	 */
	public static Date getDateFromString(String dateString) {
		Date date = null;
		if (isNotEmpty(dateString)) {
			try {
				date = dateFormat.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * Checks if string is empty
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean isNotEmpty(String string) {
		if (string != null && !string.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if string is empty
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean isEmpty(String string) {
		if (string != null && string.trim().equals("")) {
			return false;
		}
		return true;
	}
}
