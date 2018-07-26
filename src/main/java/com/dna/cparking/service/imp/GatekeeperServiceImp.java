package com.dna.cparking.service.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dna.cparking.domain.CalendarParking;
import com.dna.cparking.domain.Gatekeeper;
import com.dna.cparking.exception.type.UnabledOperationException;
import com.dna.cparking.message.CatalogMessage;
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

	public void registerVehicleEntry(Vehicle vehicle) throws UnabledOperationException {
		if (!calendarParking.isMondayOrSunday() && gatekeeper.checkPlateStartWithA(vehicle.getPlate())) {
			throw new UnabledOperationException(CatalogMessage.INVALID_PLATE_IN_DAY,  HttpStatus.NOT_ACCEPTABLE);
		}
		
		if (!gatekeeper.checkSpaceVehicleType(vehicle.getVehicleType())) {
			throw new UnabledOperationException(CatalogMessage.THERE_IS_NOT_SPACE_FOR_VEHICLE_TYPE, HttpStatus.NOT_ACCEPTABLE);
		}
		
		if (gatekeeper.checkVehicleIsParked(vehicle.getPlate())) {
			throw new UnabledOperationException(CatalogMessage.VEHICLE_ALREADY_IS_PARKED, HttpStatus.NOT_ACCEPTABLE);
		}
		
		Parking parking = new Parking();		
	
		vehicle = vehicleService.getVehicleToParking(vehicle);				
		parking = parkingService.settingParking(parking, vehicle);
		
		parkingService.saveParking(parking);
	}

	public Parking giveOutVehicle(String plate) throws UnabledOperationException  {			
		if (!gatekeeper.checkVehicleIsParked(plate)){
			throw new UnabledOperationException(CatalogMessage.VEHICLE_IS_NOT_PARKED, HttpStatus.NOT_ACCEPTABLE);
		}
		
		Date outDate;
		int payment;			
		Parking parking;
		
		outDate = Calendar.getInstance().getTime();
		parking = parkingService.getParkingToGiveOut(plate);
		payment = gatekeeper.generatePayment(parking.getVehicle().getVehicleType(), parking.getInDate(), outDate, parking.getVehicle().getDisplacement());
		
		return parkingService.parkingGiveOutById(parking,outDate, payment);
	}

	public List<Parking> findAllVehicles() throws UnabledOperationException {		
		if (gatekeeper.checkParkingIsEmpty()) {
			throw new UnabledOperationException(CatalogMessage.PARKING_IS_EMPTY, HttpStatus.NOT_ACCEPTABLE);
		}
		return parkingService.findAllParking();		
	}
}
