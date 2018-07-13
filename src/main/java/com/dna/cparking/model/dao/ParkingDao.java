package com.dna.cparking.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;

public interface ParkingDao extends CrudRepository<Parking, Long>{
	
//	Find all vehicles in parking.
	@Query("FROM Parking P WHERE P.status = :status")
	List<Parking> findAllVehicleInParking(@Param("status") boolean status);

//	Find all vehicles in parking by type
	@Query("FROM Parking P JOIN Vehicle V WHERE V.vehicleType = :vehicleType AND P.status = :status")
	List<Parking> findAllVehicleInParkingByType(@Param("vehicleType") String vehicleType,  @Param("status") boolean status);

//	Find vehicle by plate in parking
	@Query("FROM Parking P JOIN Vehicle V WHERE V.plate = :plate AND P.status = 1")
	Vehicle findVehicleInParkingByPlate(@Param("plate") String plate);
}
