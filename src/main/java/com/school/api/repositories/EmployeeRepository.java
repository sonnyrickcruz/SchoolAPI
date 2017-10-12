/**
 * 
 */
package com.school.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.school.api.entities.Employees;

/**
 * @author sonny.cruz
 *
 */
public interface EmployeeRepository extends CrudRepository<Employees, Long> {

}
