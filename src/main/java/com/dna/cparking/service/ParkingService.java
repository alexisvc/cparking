package com.dna.cparking.service;

import java.util.Date;
import java.util.List;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;

public interface ParkingService {
	
	public void saveParking(Parking parking);
	public void parkingGiveOutById(Parking parking, Date outDate, int payment);
	public Parking settingParking (Parking parking, Vehicle vehicle);
	public Parking getParkingToGiveOut(String plate);
	public List<Parking> findAllParking();
}
