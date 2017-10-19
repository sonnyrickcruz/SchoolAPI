package com.school.api.entities;

import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.CaseFormat;
import com.school.utils.StringUtils;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String username;
	private int employeeId;
	private String password;
	private String userLevel;

	@Required
	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Required
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Required
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Required
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Check if the values of required fields are satisfied.
	 * 
	 * @return boolean
	 */
	public boolean isValid() {
		boolean isValid = true;
		Class<?> thisClass = this.getClass();
		Class<?> string = String.class;
		try {
			for (Field f : thisClass.getDeclaredFields()) {
				boolean isRequired = thisClass
						.getMethod(new StringBuilder("get")
								.append(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, f.getName())).toString())
						.isAnnotationPresent(Required.class);
				if (isRequired && f.getDeclaringClass().equals(string) && StringUtils.isEmpty((String) f.get(this).toString())) {
					isValid = false;
				} else if (isRequired && f.get(this) == null) {
					isValid = false;
				}

				if (!isValid)
					break;
			}
		} catch (Exception e) {
			isValid = false;
		}
		return isValid;
	}
}
