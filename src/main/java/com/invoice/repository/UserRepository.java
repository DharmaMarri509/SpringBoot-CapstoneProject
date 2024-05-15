package com.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

	public User findByUserNameAndPassword(String userName, String password);
	
	
	public User findByUserName(String userName); //used to find the unique user name is saving or not
}
