package com.pavanit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pavanit.entity.StateMasterEntity;
@Repository
public interface StateRepository extends JpaRepository<StateMasterEntity, Integer>{
	
	// select * from state_master where country_id=? 
	public List<StateMasterEntity> findByCountryId(Integer countryId);
}
