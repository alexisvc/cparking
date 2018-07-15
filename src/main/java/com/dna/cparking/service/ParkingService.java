package com.dna.cparking.service;

import java.util.List;

import com.dna.cparking.model.entity.Parking;

public interface ParkingService {
	
	public void saveParking(Parking parking);
	public List<Parking> findAllParking();
}
