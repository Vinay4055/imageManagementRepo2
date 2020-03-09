package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.entity.User;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User fetchedUser = userService.fetchUser(userName);
		org.springframework.security.core.userdetails.User.UserBuilder builder;
		if (fetchedUser != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(userName);
			builder.password(fetchedUser.getPassword());
			builder.roles("user");

			userService.setUser(fetchedUser);

		} else {
			throw new UsernameNotFoundException("User not found.");
		}

		return builder.build();
	}

}
