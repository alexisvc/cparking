package com.dna.cparking.domain.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dna.cparking.domain.Gatekeeper;
import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.util.EnumVehicleType;
import com.dna.cparking.util.ParamsConfigParking;

@Service
@Component
public class GatekeeperImp implements Gatekeeper {

	@Autowired
	private ParkingDao parkingDao;

	public boolean checkPlateStartWithA(String plate) {
		return plate.startsWith(ParamsConfigParking.RESTRICTION_INITIAL_LETTER);
	}

	@Override
	public boolean checkSpaceVehicleType(EnumVehicleType vehicleType) {
		int vehiclesInParking = parkingDao.findAllVehicleInParkingByType(vehicleType);
		int maxVehicleAllowed = (vehicleType == EnumVehicleType.CAR ? ParamsConfigParking.MAX_CARS_ALLOWED : ParamsConfigParking.MAX_MOTORBIKES_ALLOWED);
		
		return vehiclesInParking < maxVehicleAllowed;
	}
	
	@Override
	public boolean checkVehicleIsParked(String plate) {
		return parkingDao.alreadyParked(plate);	
	}
}
