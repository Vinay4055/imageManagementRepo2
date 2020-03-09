package com.nagarro.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nagarro.entity.User;
import com.nagarro.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private User user;

	@GetMapping(value = "/")
	public String home() {

		return "redirect:/project/";
	}

	@GetMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("user",user);
		return "register";
	}

	@PostMapping(value = "/doRegister")
	public String registerUser(@Valid User user, HttpServletRequest request, Model map, BindingResult result)
			throws ServletException {

		System.out.println("UserName From CONTROLLER = " + user.getName());
		if (result.hasErrors()) {
			System.out.println("Has Errors");

			return "redirect:/login";
		}

		else if (userService.registerUser(user).equalsIgnoreCase("success")) {

			map.addAttribute("message", "Successfully registered user");
			return "redirect:/login";
		} else {
			map.addAttribute("message", "User already exists");
			return "register";
		}
	}
	
	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}
