package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.entity.User;
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

	public User findByName(String username);
	
}
