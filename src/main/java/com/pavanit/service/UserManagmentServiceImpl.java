package com.pavanit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavanit.binding.LoginForm;
import com.pavanit.binding.UnlockAccountForm;
import com.pavanit.binding.UserForm;
import com.pavanit.entity.CityMasterEntity;
import com.pavanit.entity.CountryMasterEntity;
import com.pavanit.entity.StateMasterEntity;
import com.pavanit.entity.UserDtlsEntity;
import com.pavanit.repository.CityRepository;
import com.pavanit.repository.CountryRepository;
import com.pavanit.repository.StateRepository;
import com.pavanit.repository.UserAccountRepository;
import com.pavanit.util.EmailUtils;

@Service
public class UserManagmentServiceImpl implements UserManagmentService {

	@Autowired
	private UserAccountRepository userAccountRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String login(LoginForm loginform) {

		UserDtlsEntity entity = userAccountRepo.findByEmailAndPassword(loginform.getEmail(), loginform.getPwd());

		if (entity == null) {
			return "Invalid Credentials";
		}

		if (entity != null && entity.getAccStatus().equals("LOCKED")) {
			return "Your Account is Locked";
		}
		return "SUSSESS";
	}

	@Override
	public String emailCheck(String emailId) {

		UserDtlsEntity entity = userAccountRepo.findByEmail(emailId);
		if (entity == null) {
			return "UNIQUE";
		}
		return "EMAIL IS DUPLICATE";
	}

	@Override
	public Map<Integer, String> loadCountries() {

		java.util.List<CountryMasterEntity> countries = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();

		for (CountryMasterEntity country : countries) {
			
			countryMap.put(country.getCountryId(), country.getCountryName());
		}

		return countryMap;
	}
	
	@Override
	public Map<Integer, String> loadStates(Integer countryId) {

		java.util.List<StateMasterEntity> states = stateRepo.findByCountryId(countryId);

		Map<Integer, String> stateMap = new HashMap<>();

		for (StateMasterEntity state : states) {
			stateMap.put(state.getCountryId(), state.getStateName());

		}
		return stateMap;

	}

	/*
	 * @Override public Map<Integer, String> loadStatesMap(Integer countryId) {
	 * 
	 * java.util.List<StateMasterEntity> states =
	 * stateRepo.findByCountryId(countryId);
	 * 
	 * Map<Integer, String> stateMap = new HashMap<>();
	 * 
	 * for (StateMasterEntity state : states) { stateMap.put(state.getCountryId(),
	 * state.getStateName());
	 * 
	 * } return stateMap; }
	 */

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {

		java.util.List<CityMasterEntity> cities = cityRepo.findByStateId(stateId);

		Map<Integer, String> cityMap = new HashMap<>();

		for (CityMasterEntity city : cities) {
			cityMap.put(city.getCityId(), city.getCityName());
		}
		return cityMap;
	}

	@Override
	public String registerUser(UserForm userFrom) {

		UserDtlsEntity entity = new UserDtlsEntity();

		BeanUtils.copyProperties(userFrom, entity);

		entity.setAccStatus("LOCKED");

		entity.setPassword(generateRandomPwd());

		UserDtlsEntity savedEntity = userAccountRepo.save(entity);

		String email = userFrom.getEmail();
		String subject = "User Registration - Pavan IT";

		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBodyContent(fileName, entity);

		Boolean isSent = emailUtils.sendEmail(email, subject, body);

		if (savedEntity.getUserId() != null && isSent) {
			return "RECORD IS INSERTED SUSSECFULLY";

		}
		return "FAIL";
	}

	@Override
	public String unlockAccount(UnlockAccountForm unlockAccountForm) {

		if (!unlockAccountForm.getNewPwd().equals(unlockAccountForm.getConfirmNewPwd())) {
			return "Password and Confirm Password Should be same";
		}

		UserDtlsEntity entity = userAccountRepo.findByEmailAndPassword(unlockAccountForm.getEmail(),
				unlockAccountForm.getTempPwd());

		if (entity == null) {
			return "Incorrect Temporary Password";
		}

		entity.setPassword(unlockAccountForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");

		userAccountRepo.save(entity);
		return "Account Unlocked";

	}

	@Override
	public String forgetPwd(String emailId) {

		UserDtlsEntity entity = userAccountRepo.findByEmail(emailId);

		if (entity == null) {

			return "Invalid Email Id";
		}

		String subject = "Recover Password - Pavan IT";

		String fileName = "RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBodyContent(fileName, entity);

		Boolean isSent = emailUtils.sendEmail(emailId, subject, body);

		if (isSent) {
			return "Password sent to registered email";

		}

		return "ERROR";
	}

	// Method For Generating Ramdon Password
	public String generateRandomPwd() {
		
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	private String readMailBodyContent(String fileName, UserDtlsEntity entity) {

		String mailBody = null;
		try {

			StringBuffer sb = new StringBuffer();

			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine(); // Reading a First line data

			while (line != null) {

				sb.append(line); // Appending line data to buffer obj
				line = br.readLine(); // Reading next line data

			}

			mailBody = sb.toString();
			mailBody = mailBody.replace("{FNAME}", entity.getFName());
			mailBody = mailBody.replace("{LNAME}", entity.getLName());
			mailBody = mailBody.replace("{TEMP-PWD}", entity.getPassword());
			mailBody = mailBody.replace("{EMAIL}", entity.getEmail());

			br.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return mailBody;

	}

}
