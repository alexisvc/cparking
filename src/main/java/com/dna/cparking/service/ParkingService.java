package com.dna.cparking.service;

import java.util.List;

import com.dna.cparking.model.entity.Parking;

public interface ParkingService {
	public Parking saveParking(Parking parking);
	public List<Parking> findAllVehiclesParking();
}
