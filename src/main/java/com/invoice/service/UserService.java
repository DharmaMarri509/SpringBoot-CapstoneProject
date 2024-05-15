package com.invoice.service;

import com.invoice.entity.User;
import com.invoice.exception.BlankNameException;
import com.invoice.exception.UserAlreadyExistException;
import com.invoice.exception.UserNotFoundException;

public interface UserService {

	public User saveUser(User user) throws BlankNameException, UserAlreadyExistException;
	
	public User getUser(String userName, String password) throws UserNotFoundException;
}
