package com.pavanit.service;

import java.util.Map;

import com.pavanit.binding.LoginForm;
import com.pavanit.binding.UnlockAccountForm;
import com.pavanit.binding.UserForm;

public interface UserManagmentService {

	// Login Functionality Method
	public String login(LoginForm loginform);

	// Registration Functionality Method
	public String emailCheck(String emailId);

	public Map<Integer, String> loadCountries();

	public Map<Integer, String> loadStates(Integer countryId);

	public Map<Integer, String> loadCities(Integer stateId);

	public String registerUser(UserForm userFrom);

	// Unlock Account Functionality Method
	public String unlockAccount(UnlockAccountForm unlockAccountForm);

	// Forgot Password Functionality Method
	public String forgetPwd(String email);
}
