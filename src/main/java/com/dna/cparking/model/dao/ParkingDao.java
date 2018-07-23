package com.dna.cparking.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.util.EnumVehicleType;

public interface ParkingDao extends CrudRepository<Parking, Long>{
	
	@Query("SELECT  p FROM Parking p JOIN p.vehicle v WHERE p.status = true")
	List<Parking> findAllVehiclesInParking();

	@Query("SELECT COUNT(*) FROM Parking p JOIN p.vehicle v WHERE v.vehicleType = :vehicleType AND p.status = true")
	int findAllVehicleInParkingByType(@Param("vehicleType") EnumVehicleType vehicleType);
	
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Parking p JOIN p.vehicle v WHERE v.plate = :plate AND p.status = true")
	boolean alreadyParked(@Param("plate") String plate);

	@Query("SELECT CASE WHEN COUNT(p) <= 0 THEN true ELSE false END FROM Parking p JOIN p.vehicle v WHERE p.status = true")
	boolean parkingIsEmpty();
	
	@Query("FROM Parking P JOIN P.vehicle V WHERE V.plate = :plate AND P.status = true")
	Parking findVehicleInParkingByPlate(@Param("plate") String plate);
} 
