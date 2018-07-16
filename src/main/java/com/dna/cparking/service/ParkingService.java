package com.dna.cparking.service;

import java.util.List;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;

public interface ParkingService {
	
	public void saveParking(Parking parking);
	public Parking settingParking (Parking parking, Vehicle vehicle);
	public List<Parking> findAllParking();
}
