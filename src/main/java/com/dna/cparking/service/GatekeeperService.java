package com.dna.cparking.service;

import java.util.Set;

import com.dna.cparking.model.entity.Vehicle;

public interface GatekeeperService {
	public void enterVehicle(Vehicle vehicle);
	public void giveOutVehicle(Vehicle vehicle);
	public Set<Vehicle> findAllVehicle();
}	
