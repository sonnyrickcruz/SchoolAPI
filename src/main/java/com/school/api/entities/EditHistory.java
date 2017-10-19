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
public class EditHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int editHistoryId;
	private int id;
	private String type;
	private String action;
	private String executorId;

	public int getEditHistoryId() {
		return editHistoryId;
	}

	public void setEditHistoryId(int editHistoryId) {
		this.editHistoryId = editHistoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Required
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getExecutorId() {
		return executorId;
	}

	public void setExecutorId(String executorId) {
		this.executorId = executorId;
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
