package com.pavanit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pavanit.binding.UserForm;
import com.pavanit.service.UserManagmentService;

@RestController
public class UserRegistrationController {
	
	@Autowired
    private UserManagmentService userManagementService;



   @GetMapping("/email/{emailId}")
    public String emailCheck(@PathVariable("emailId") String emailId) {
        return userManagementService.emailCheck(emailId);
    }



   @GetMapping("/Countries")
    public Map<Integer, String> getCountry() {
        return userManagementService.loadCountries();
    }
   
   @GetMapping("/state/{counyryId}")
   public Map<Integer, String> getState(@PathVariable("counyryId") Integer counyryId) {
       return userManagementService.loadStates(counyryId);
   }



  @GetMapping("/city/{stateId}")
   public Map<Integer, String> getCity(@PathVariable("stateId") Integer stateId) {
       return userManagementService.loadCities(stateId);
   }



  @PostMapping("/user")
   public String userRegister(@RequestBody UserForm userForm) {
       return userManagementService.registerUser(userForm);
   }


}
