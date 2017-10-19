package com.school.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.constants.Constants;
import com.school.api.entities.Employee;
import com.school.api.repositories.EmployeeRepository;
import com.school.utils.StringUtils;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	private EmployeeController() {}
	
	@Autowired
	private EmployeeRepository EmployeeRepository;
	
	@RequestMapping(method = RequestMethod.POST, path="/add")
	@ResponseBody public String addEmployee(@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String middleName,
			@RequestParam String birthday,
			@RequestParam String sex,
			@RequestParam String executorId) {
		String result = Constants.FAIL;
		try {
			Employee employees = new Employee();
			employees.setFirstName(firstName);
			employees.setMiddleName(middleName);
			employees.setLastName(lastName);
			employees.setSex(sex);
			employees.setBirthday(StringUtils.getDateFromString(birthday));
			if (employees.isValid()){
				employees = EmployeeRepository.save(employees);
				if (employees != null) {
					result = Constants.SUCCESS;
				}
			} else {
				result = Constants.INPUT;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="/edit")
	@ResponseBody public String editEmployee(@RequestParam int employeeId,
			@RequestParam String firstName, 
			@RequestParam String lastName,
			@RequestParam String middleName,
			@RequestParam String birthday,
			@RequestParam String sex,
			@RequestParam String executorId) {
		String result = Constants.FAIL;
		try {
			Employee employees = EmployeeRepository.findOne(employeeId);
			if (employees != null) {
				employees.setFirstName(firstName);
				employees.setMiddleName(middleName);
				employees.setLastName(lastName);
				employees.setSex(sex);
				employees.setBirthday(StringUtils.getDateFromString(birthday));
			}
			if (employees != null && employees.isValid()){
				employees = EmployeeRepository.save(employees);
				if (employees != null) {
					result = Constants.SUCCESS;
				}
			} else {
				result = Constants.INPUT;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/remove")
	public String removeEmployee(@RequestParam int employeeId) {
		String result = Constants.FAIL;
		try {
			Employee employee = EmployeeRepository.findOne(employeeId);
			if (employee != null && Constants.ACTIVE_STATUS.equalsIgnoreCase(employee.getStatus())) {
				employee.setStatus(Constants.INACTIVE_STATUS);
				employee = EmployeeRepository.save(employee);
				if (employee != null) {
					result = Constants.SUCCESS;
				}
			} else {
				result = Constants.INPUT;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/activate")
	public String activateEmployee(@RequestParam int employeeId) {
		String result = Constants.FAIL;
		try {
			Employee employee = EmployeeRepository.findOne(employeeId);
			if (employee != null && Constants.INACTIVE_STATUS.equalsIgnoreCase(employee.getStatus())) {
				employee.setStatus(Constants.ACTIVE_STATUS);
				employee = EmployeeRepository.save(employee);
				if (employee != null) {
					result = Constants.SUCCESS;
				}
			} else {
				result = Constants.INPUT;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	@GetMapping(path="/all")
	@ResponseBody public Iterable<Employee> getAllEmployee() {
		try {
			return EmployeeRepository.findAll();
		} catch (Exception e) {
			return null;
		}
	}
}