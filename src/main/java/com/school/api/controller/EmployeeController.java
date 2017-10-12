package com.school.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.constants.Constants;
import com.school.api.entities.Employees;
import com.school.api.repositories.EmployeeRepository;
import com.school.utils.StringUtils;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	private EmployeeController() {}
	
	@Autowired
	private EmployeeRepository EmployeeRepository;
	
	@RequestMapping(method = RequestMethod.GET, path="/add")
	@ResponseBody public String addUser(@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String middleName,
			@RequestParam String birthday,
			@RequestParam String sex) {
		Employees employees = new Employees();
		employees.setFirstName(firstName);
		employees.setMiddleName(middleName);
		employees.setLastName(lastName);
		employees.setSex(sex);
		employees.setBirthday(StringUtils.getDateFromString(birthday));
		employees = EmployeeRepository.save(employees);
		
		if (employees != null) {
			return Constants.SUCCESS;
		} else {
			return Constants.FAIL;
		}
	}
	
	@GetMapping(path="/all")
	@ResponseBody public Iterable<Employees> getAllUsers() {
		return null;
	}
}