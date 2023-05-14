package com.pavanit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {
	
private Integer userId;
	
	private String fName;
	
	private String lName;
	
	private String email;
	
	private String password;
	
	private Long phno;
	
	private LocalDate dob;
	
	private String gender;
	
	private String cityId;
	
	private Integer stateId;
	
	private Integer countryId;	

}
