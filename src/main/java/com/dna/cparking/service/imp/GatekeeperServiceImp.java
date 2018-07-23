package com.dna.cparking.service.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dna.cparking.domain.CalendarParking;
import com.dna.cparking.domain.Gatekeeper;
import com.dna.cparking.exception.ExceptionParking;
import com.dna.cparking.message.CatalogMessages;
import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.service.GatekeeperService;
import com.dna.cparking.service.ParkingService;
import com.dna.cparking.service.VehicleService;

@Service
public class GatekeeperServiceImp implements GatekeeperService {
	
	@Autowired
	private Gatekeeper gatekeeper;
	
	@Autowired
	private CalendarParking calendarParking;
	
	@Autowired
	private ParkingService parkingService;
	
	@Autowired
	private VehicleService vehicleService;
	
	public void registerVehicleEntry(Vehicle vehicle){
		if (calendarParking.isMondayOrSunday() && gatekeeper.checkPlateStartWithA(vehicle.getPlate())) {
			throw new ExceptionParking(CatalogMessages.INVALID_PLATE_IN_DAY);
		}
		
		if (!gatekeeper.checkSpaceVehicleType(vehicle.getVehicleType())) {
			throw new ExceptionParking(CatalogMessages.THERE_IS_NOT_SPACE_FOR_VEHICLE_TYPE);
		}
		
		if (gatekeeper.checkVehicleIsParked(vehicle.getPlate())){
			throw new ExceptionParking(CatalogMessages.VEHICLE_ALREADY_IS_PARKED);
		}
		
		Parking parking = new Parking();
		
		vehicle = vehicleService.getVehicleToParking(vehicle);				
		parking = parkingService.settingParking(parking, vehicle);
		
		parkingService.saveParking(parking);
	}

	public void giveOutVehicle(String plate) {			
		if (!gatekeeper.checkVehicleIsParked(plate)){
			throw new ExceptionParking(CatalogMessages.VEHICLE_IS_NOT_PARKED);
		}
		
		Date outDate;
		int payment;			
		Parking parking;
		
		outDate = Calendar.getInstance().getTime();
		parking = parkingService.getParkingToGiveOut(plate);
		payment = gatekeeper.generatePayment(parking.getVehicle().getVehicleType(), parking.getInDate(), outDate, parking.getVehicle().getDisplacement());
		
		parkingService.parkingGiveOutById(parking,outDate, payment);
	}

	public List<Parking> findAllVehicles(){		
		if (gatekeeper.checkParkingIsEmpty()) {
			throw new ExceptionParking(CatalogMessages.PARKING_IS_EMPTY);
		}
		return parkingService.findAllParking();		
	}
}
