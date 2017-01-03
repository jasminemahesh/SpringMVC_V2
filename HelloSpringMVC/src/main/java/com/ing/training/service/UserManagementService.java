package com.ing.training.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ing.training.dao.UserManagementDao;
import com.ing.training.domain.User;
import com.ing.training.exception.UserCreationException;
import com.ing.training.exception.UserFetchException;

@Service
public class UserManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	@Autowired
	UserManagementDao userManagementDao;
	
	
	public User createUser(User user)
	{
		try
		{
		return userManagementDao.createUser(user);
		}
		catch(DataAccessException dae)
		{
			logger.error("Error in creation of user", dae);
			throw new UserCreationException("Error in creation of user", dae);
		}
	}
	
	public User getUserById(int id)
	{
		//return populateUser(id);
		
		User user=new User();
		try
		{
		user=userManagementDao.getUserById(id);
		}
		catch(DataAccessException dae)
		{
			logger.error("Error in Fetching User Details for UserId: "+ id , dae);
			throw new UserFetchException("Error in Fetching User Details for UserId: "+ id, dae);
		}
		return user;
	}
	
	public List<User> listUsers()
	{
		List<User> userList=new ArrayList<>();
		try
		{
			userList=userManagementDao.listUsers();
		}
		catch(DataAccessException dae)
		{
			logger.error("No User Found", dae);
			throw new UserFetchException("No User Found", dae);
		}
		return userList;
	}
	
	private User populateUser(int id)
	{
		User user=new User();
		user.setFirstname("John");
		user.setLastname("Kim");
		user.setCity("Mumbai");
		user.setId(id);
		
		return user;
		
	}

}
