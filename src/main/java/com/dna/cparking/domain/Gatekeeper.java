package com.dna.cparking.domain;

import java.util.Date;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.util.EnumVehicleType;

public interface Gatekeeper {
	
	public boolean checkPlateStartWithA (String plate);	
	public boolean checkSpaceVehicleType(EnumVehicleType vehicleType);
	public boolean checkVehicleIsParked(String plate);
	public boolean checkParkingIsEmpty();
	
	public Parking getParkingToGiveOutVehicle(String plate);
	public int generatePayment(EnumVehicleType vehicleType, Date inDate, Date outDate, int displacement);
	public int calculatePayment(Date inDate, Date outDate, int dayValue, int hourValue);
}
