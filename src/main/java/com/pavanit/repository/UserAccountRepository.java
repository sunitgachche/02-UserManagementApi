package com.pavanit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pavanit.entity.UserDtlsEntity;
@Repository
public interface UserAccountRepository extends JpaRepository<UserDtlsEntity, Integer> {
	
	//select * from user_accounts where user_email=? and user_pwd=?
	public UserDtlsEntity findByEmailAndPassword(String email, String pwd);
	
	//select * from user_accounts where user_email=? 
	public UserDtlsEntity findByEmail(String emailId);
 
}
