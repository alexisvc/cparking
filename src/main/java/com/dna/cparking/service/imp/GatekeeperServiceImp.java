package com.dna.cparking.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dna.cparking.domain.CalendarParking;
import com.dna.cparking.domain.Gatekeeper;
import com.dna.cparking.model.dao.VehicleDao;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.service.ParkingService;

@Service
public class GatekeeperServiceImp {
	@Autowired
	private Gatekeeper gatekeeper;
	
	@Autowired
	private CalendarParking calendarParking;
	
	@Autowired
	private ParkingService parkingService;

	@Autowired
	private VehicleDao vehicleDao;
	
	
	public void enterVehicle(Vehicle vehicle) {
		if (calendarParking.isMondayOrSunday() && gatekeeper.checkPlateStartWithA(vehicle.getPlate())) {
			//BusinessLogic
		}
	}
/*
	public void giveOutVehicle(Vehicle vehicle) {
		// BusinessLogic
	}
	
	public List<Vehicle> findAllVehicle(){
		// BusinessLogic
	}
*/
}
