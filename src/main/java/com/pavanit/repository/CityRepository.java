package com.pavanit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pavanit.entity.CityMasterEntity;




@Repository
public interface CityRepository extends JpaRepository<CityMasterEntity, Integer> {
	
	// select * from city_master where state_Id=?
	public List<CityMasterEntity> findByStateId(Integer stateId);

}
