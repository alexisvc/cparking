package com.dna.cparking.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dna.cparking.model.dao.VehicleDao;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.service.VehicleService;

@Service
public class VehicleServiceImp implements VehicleService{
	
	@Autowired
	private VehicleDao vehicleDao;

//	If the vehicle is new, create it.
//	If the vehicle was in parking, get it.
	@Override
	@Transactional
	public Vehicle getVehicleToParking(Vehicle vehicle) {
		
		if (!vehicleDao.existsByPlate(vehicle.getPlate())){
			vehicle = vehicleDao.save(vehicle);
		} else {
			vehicle = vehicleDao.getVehicleByPlate(vehicle.getPlate());
		}
		return vehicle;	
	}	
}
