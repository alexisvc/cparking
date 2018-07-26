package com.dna.cparking.service;

import java.util.List;

import com.dna.cparking.exception.ApiErrorBuilderException;
import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;

public interface GatekeeperService {
	public void registerVehicleEntry(Vehicle vehicle) throws ApiErrorBuilderException ;
	public Parking giveOutVehicle(String plate) throws ApiErrorBuilderException;
	public List<Parking> findAllVehicles() throws ApiErrorBuilderException;
}	
