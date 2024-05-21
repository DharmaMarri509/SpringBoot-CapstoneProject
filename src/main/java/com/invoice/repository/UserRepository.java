package com.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.invoice.entity.User;

public interface UserRepository  extends CrudRepository<User, Integer>{

	public Integer findByUserNameAndPassword(String userName, String password);
	
	
	public User findByUserName(String userName); //used to find the unique user name is saving or not
}
