package com.dna.cparking.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dna.cparking.model.dao.VehicleTypeDao;
import com.dna.cparking.model.entity.VehicleType;
import com.dna.cparking.service.VehicleTypeService;

@Service
public class VehicleTypeServiceImp implements VehicleTypeService {
	
	@Autowired
	private VehicleTypeDao vehicleTypeDao;
	
	@Override
	public VehicleType saveVehicleType(VehicleType vehicleType) {
		return vehicleTypeDao.save(vehicleType);
	}
}
