package com.pavanit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavanit.binding.UnlockAccountForm;
import com.pavanit.service.UserManagmentService;

@RestController
public class UnlockRestController {
	
	@Autowired
    private UserManagmentService service;



   @PostMapping("/unlock")
    public String unlockAccount(@RequestBody UnlockAccountForm unlockAccountForm) {
        return service.unlockAccount(unlockAccountForm);
    }

}
