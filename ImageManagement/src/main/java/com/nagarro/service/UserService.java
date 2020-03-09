package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.dao.UserDao;
import com.nagarro.entity.User;

@Service
public class UserService {
	@Autowired
	User user;
	
	

	@Autowired
	private UserDao userDao;

	public User fetchUser(String userName) {
		return userDao.findByName(userName);

	}

	public String registerUser(User user) {
		User foundUser = fetchUser(user.getName());
		if (foundUser == null) {

			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			userDao.save(user);
			return "success";
		}
		return "already exists";
	}

	public void setUser(User fetchedUser) {
		user=fetchedUser;
		
	}
	public User getUser() {
		return user;
	}
	

}
