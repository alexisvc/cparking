package com.dna.cparking.domain.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dna.cparking.domain.Gatekeeper;
import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.util.EnumVehicleType;
import com.dna.cparking.util.ParamsConfigParking;

@Component
public class GatekeeperImp implements Gatekeeper {

	@Autowired
	private ParkingDao parkingDao;

	public boolean checkPlateStartWithA(String plate) {
		return plate.startsWith(ParamsConfigParking.RESTRICTION_INITIAL_LETTER);
	}

	@Override
	public boolean checkSpaceVehicleType(EnumVehicleType vehicleType) {
		int numberVehicles = parkingDao.findAllVehicleInParkingByType(vehicleType).size();
		boolean thereIsSpace = false;
		
		if ((vehicleType.equals(EnumVehicleType.CAR) && numberVehicles < ParamsConfigParking.MAX_CARS_ALLOWED) || 
			(vehicleType.equals(EnumVehicleType.MOTORBIKE) && numberVehicles < ParamsConfigParking.MAX_MOTORBIKES_ALLOWED)) { 
			thereIsSpace = true; 
		}
		
		return thereIsSpace;		
	}
	
	@Override
	public boolean checkVehicleIsParked(String plate) {
		return parkingDao.alreadyParked(plate);	
	}
}
