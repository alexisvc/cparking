package com.dna.cparking.service;

import java.util.List;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;

public interface GatekeeperService {
	public void registerVehicleEntry(Vehicle vehicle);
	public Parking giveOutVehicle(String plate);
	public List<Parking> findAllVehicles();
}	
