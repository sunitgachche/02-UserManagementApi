package com.pavanit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "USER_ACCOUNT")
@Data
public class UserDtlsEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "FIRST_NAME")
	private String fName;
	
	@Column(name = "LAST_NAME")
	private String lName;
	
	@Column(name = "USER_EMAIL", unique = true)
	private String email;
	
	@Column(name = "USER_PWD")
	private String password;
	
	@Column(name = "USER_MOBILE")
	private Long phno;
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "CITY_NAME")
	private String cityId;
	
	@Column(name = "STATE_ID")
	private Integer stateId;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	@Column(name = "CREATED_DATE", updatable = false )
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name = "UPDATED_DATE", insertable = false )
	@CreationTimestamp
	private LocalDate updatedDate;
	
	
	
	
	
	


}
