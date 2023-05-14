package com.pavanit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavanit.binding.LoginForm;
import com.pavanit.service.UserManagmentService;

@RestController
public class LoginRestController {
	 @Autowired
	 private UserManagmentService userManagementService;



	   @PostMapping("/login")
	    public String login(@RequestBody LoginForm loginForm) {
	        return userManagementService.login(loginForm);
	    }
	
}

 
