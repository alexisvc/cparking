package com.dna.cparking.service;

import java.util.List;

import com.dna.cparking.exception.type.UnabledOperationException;
import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;

public interface GatekeeperService {
	public void registerVehicleEntry(Vehicle vehicle) throws UnabledOperationException ;
	public Parking giveOutVehicle(String plate) throws UnabledOperationException;
	public List<Parking> findAllVehicles() throws UnabledOperationException;
}	
