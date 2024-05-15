package com.invoice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.invoice.entity.User;
import com.invoice.exception.BlankNameException;
import com.invoice.exception.RecordNotFoundException;
import com.invoice.exception.UserAlreadyExistException;
import com.invoice.exception.UserNotFoundException;
import com.invoice.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	private UserRepository repo;
	
	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	UserServiceImpl(UserRepository repo){
		this.repo = repo;
	}
	
	@Override
	public User saveUser(User user) throws BlankNameException, UserAlreadyExistException{
		User findByUserName = repo.findByUserName(user.getUserName());
		if(findByUserName!=null) {
			
			log.info("user is not registered successfully");
			throw new UserAlreadyExistException("user already found with name :- "+user.getUserName());
		}else {
			if(user.getUserName().equals("")) {
				throw new BlankNameException("name should not be blank");
			}
			log.info("user registered successfully");
			return repo.save(user);
		}
	}
	
	@Override
	public User getUser(String userName, String password) throws UserNotFoundException {
		User user = repo.findByUserName(userName);
		if(userName.equals("") || password.equals("")) {
			throw new UserNotFoundException("dont pass empty values for the username and password");
		}
		
		String name = user.getUserName();
		String pwd = user.getPassword();
		if(userName.equals(name) && password.equals(pwd)) {
			return repo.findByUserNameAndPassword(userName, password);
		}else {
			throw new UserNotFoundException("no user matched with the entered username and password");
		}
		
	}
}
		
	

