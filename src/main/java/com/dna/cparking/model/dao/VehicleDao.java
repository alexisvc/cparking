package com.dna.cparking.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.dna.cparking.model.entity.Vehicle;

public interface VehicleDao extends CrudRepository<Vehicle, Long>{

}
