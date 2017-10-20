package com.school.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.constants.Constants;
import com.school.api.entities.User;
import com.school.api.repositories.EmployeeRepository;
import com.school.api.repositories.UserRepository;

@RestController
@RequestMapping("User")
public class UserController {
	private UserController() {
	}

	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	EmployeeRepository employeeRepository;

	@RequestMapping(method = RequestMethod.POST, path = "/add")
	@ResponseBody public String addUser(@RequestParam String username,
			@RequestParam String password,
			@RequestParam String userLevel,
			@RequestParam int employeeId,
			@RequestParam int executorId) {
		String result = Constants.FAIL;
		try {
			User user = new User();
			user.setEmployeeId(employeeId);
			user.setPassword(password);
			user.setUsername(username);
			user.setUserLevel(userLevel);
			if (user.isValid()) {
				user.setEmployee(employeeRepository.findOne(employeeId));
				user = userRepository.save(user);
				if (user != null) {
					result = Constants.SUCCESS;
				}
			} else {
				result = Constants.INPUT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/retrieve")
	@ResponseBody public User getUser(@RequestParam int userId) {
		User user = null;
		try {
			user = userRepository.findOne(userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}
	
	
}
