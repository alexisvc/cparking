package com.dna.cparking.service;

import com.dna.cparking.model.entity.Vehicle;

public interface GatekeeperService {
	public void registerVehicleEntry(Vehicle vehicle);
	public void giveOutVehicle(String plate);
//	public Set<Vehicle> findAllVehicles();
}	
