package com.dna.cparking.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dna.cparking.model.entity.Vehicle;

public interface VehicleDao extends CrudRepository<Vehicle, Long>{

//	Find vehicle by plate
	@Query("FROM Vehicle V WHERE V.plate = :plate")
	Vehicle getVehicleByPlate(@Param("plate") String plate);

//	Get in boolean existence of the a vehicle by plate
	@Query("SELECT CASE WHEN COUNT(V) > 0 THEN true ELSE false END FROM Vehicle V WHERE V.plate = :plate")
	boolean existsByPlate(@Param("plate") String plate);
}
