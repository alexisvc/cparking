package com.dna.cparking.domain;

import com.dna.cparking.util.EnumVehicleType;

public interface Gatekeeper {
	
	public boolean checkPlateStartWithA (String plate);	
	public boolean checkSpaceVehicleType(EnumVehicleType vehicleType);
	public boolean checkVehicleIsParked(String plate);

	//	public boolean generatePayment(Parking parking);
}
