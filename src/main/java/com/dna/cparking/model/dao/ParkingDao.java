package com.dna.cparking.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.util.EnumVehicleType;

public interface ParkingDao extends CrudRepository<Parking, Long>{
	
//	Find all vehicles in parking.
	@Query("FROM Parking P WHERE P.status = true")
	List<Parking> findAllVehicleInParking();

//	Find all vehicles in parking by type
	@Query("SELECT COUNT(*) FROM Parking p JOIN p.vehicle v WHERE v.vehicleType = :vehicleType AND p.status = true")
	int findAllVehicleInParkingByType(@Param("vehicleType") EnumVehicleType vehicleType);
	
//	This vehicle is already parked ?
	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Parking p JOIN p.vehicle v WHERE v.plate = :plate AND p.status = true")
	boolean alreadyParked(@Param("plate") String plate);
}
