package com.pavanit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pavanit.service.UserManagmentService;

@RestController
public class ForgetPwdRestController {



	   @Autowired
	    private UserManagmentService service;



	   @GetMapping("/forgetpwd/{email}")
	    public String forgetPwd(@PathVariable("email") String email) {
	        return service.forgetPwd(email);
	    }



	}
