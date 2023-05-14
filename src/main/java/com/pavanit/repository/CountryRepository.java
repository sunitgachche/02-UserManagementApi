package com.pavanit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pavanit.entity.CountryMasterEntity;
@Repository
public interface CountryRepository extends JpaRepository<CountryMasterEntity, Integer>{

}
