package com.dna.cparking.domain.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dna.cparking.domain.ClockParking;
import com.dna.cparking.domain.Gatekeeper;
import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.util.EnumVehicleType;
import com.dna.cparking.util.ParamsConfigParking;

@Component
public class GatekeeperImp implements Gatekeeper {

	@Autowired
	private ParkingDao parkingDao;
	
	@Autowired
	private ClockParking clock;

	public boolean checkPlateStartWithA(String plate) {
		return plate.startsWith(ParamsConfigParking.RESTRICTION_INITIAL_LETTER);
	}

	@Override
	public boolean checkSpaceVehicleType(EnumVehicleType vehicleType) {
		int vehiclesInParking = parkingDao.findAllVehicleInParkingByType(vehicleType);
		int maxVehicleAllowed = (vehicleType == EnumVehicleType.CAR ? ParamsConfigParking.MAX_CARS_ALLOWED : ParamsConfigParking.MAX_MOTORBIKES_ALLOWED);
		
		return (vehiclesInParking < maxVehicleAllowed);
	}
	
	@Override
	public boolean checkVehicleIsParked(String plate) {
		return parkingDao.alreadyParked(plate);	
	}
	
	@Override
	public Parking getParkingToGiveOutVehicle(String plate) {
		return parkingDao.findVehicleInParkingByPlate(plate);
	}
	
	@Override
	public int calculatePayment(Date inDate, Date outDate, int dayValue, int hourValue) {
		int payment = 0;
		TimerParking timerParking = clock.settingTimerParking(clock.getMinutesInParking(inDate, outDate));		
		payment = (timerParking.getDays() * dayValue) + (timerParking.getHours() * hourValue);
		
		return payment;
		
	}
	
	@Override
	public int generatePayment(EnumVehicleType vehicleType, Date inDate, Date outDate, int displacement) {
		int payment = 0;
		
		if (vehicleType.equals(EnumVehicleType.MOTORBIKE)) {
			payment = calculatePayment(inDate, outDate, ParamsConfigParking.VALUE_DAY_MOTORBIKE, ParamsConfigParking.VALUE_HOUR_MOTORBIKE);
			payment += displacement > ParamsConfigParking.VALUE_SURCHARGE_MOTORBIKE_500CC
					? ParamsConfigParking.VALUE_SURCHARGE_MOTORBIKE_500CC
					: 0;
		
		} else if (vehicleType.equals(EnumVehicleType.CAR)) {
			payment = calculatePayment(inDate, outDate, ParamsConfigParking.VALUE_DAY_MOTORBIKE, ParamsConfigParking.VALUE_HOUR_MOTORBIKE);
		}
		
		return payment;
	}
}
