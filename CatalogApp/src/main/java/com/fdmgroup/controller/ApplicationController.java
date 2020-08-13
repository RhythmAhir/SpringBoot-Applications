package com.fdmgroup.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.model.User;
import com.fdmgroup.services.UserService;

@Controller
public class ApplicationController {
	
	@Autowired
	private UserService userService;

	@RequestMapping({"/login"})
	public String Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "login";
	}
	
	@RequestMapping({"/logout"})
	public String logout(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "login";
	}
	
	@RequestMapping("/login-user")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request){
		if(userService.findByUsernameAndPassword(user.getusername(), user.getPassword())!=null) {
		return "user";
		}else {
			request.setAttribute("error", "Invalid Email or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "login";
		}
		
	}
	
	@GetMapping("/")
	public String hello(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "index";
	}
	
	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "register";
			}
	
	@RequestMapping("/show-users")
	public String all(HttpServletRequest request) {
		request.setAttribute("users", userService.showAllUser());
		request.setAttribute("mode", "ALL_USERS");
		return "all";
	}
	
	
	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request){
		userService.saveMyUser(user);
		request.setAttribute("mode", "MODE_HOME");
		return "index";
	}
	
	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request){
		userService.deleteMyUser(id);
		return "index";
	}
	
	@RequestMapping("/update-users")
	public String editUser(@RequestParam int id, HttpServletRequest request){
		request.setAttribute("user", userService.editMyUser(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "update";
	}
	
}
