package com.dna.cparking.service.imp;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dna.cparking.domain.CalendarParking;
import com.dna.cparking.domain.Gatekeeper;
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
	
	@Override
	public void enterVehicle(Vehicle vehicle) {
		
		if (!calendarParking.isMondayOrSunday() && gatekeeper.checkPlateStartWithA(vehicle.getPlate())) {
			System.out.println("******************************  PLACA NO AUTORIZADA PARA INGRESAR  ******************************");
		} else {
			if (gatekeeper.checkSpaceVehicleType(vehicle.getVehicleType()) && !gatekeeper.checkVehicleIsParked(vehicle.getPlate())) {
				try {					
					Parking parking = new Parking();				
					vehicle = vehicleService.getVehicleToParking(vehicle);				

					parking.setVehicle(vehicle);
					parking.setInDate(Calendar.getInstance().getTime());
					parking.setOutDate(null);
					parking.setPayment(0);
					parking.setStatus(true);
					
					parkingService.saveParking(parking);
					
				} catch (Exception e) {
					System.out.print(e.toString());
				}		
			}
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
