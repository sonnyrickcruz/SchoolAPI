package com.school.api.entities;

import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.CaseFormat;
import com.school.utils.StringUtils;

import javax.persistence.GenerationType;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String sex;
	private Date birthday;
	private String addressId;
	private String type;
	private String status;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	@Required
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Required
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Required
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Required
	public Date getBirthday() {
		return birthday;
	}

	@Temporal(TemporalType.DATE)
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
