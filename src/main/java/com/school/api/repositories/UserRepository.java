package com.school.api.repositories;

import org.springframework.data.repository.CrudRepository;

import com.school.api.entities.User;

/**
 * @author sonny.cruz
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
