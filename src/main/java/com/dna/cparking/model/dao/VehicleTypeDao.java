package com.dna.cparking.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.dna.cparking.model.entity.VehicleType;

public interface VehicleTypeDao extends CrudRepository<VehicleType, Long>{
	
}
