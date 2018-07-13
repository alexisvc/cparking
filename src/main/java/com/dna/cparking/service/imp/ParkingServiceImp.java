package com.dna.cparking.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.service.ParkingService;

@Service
public class ParkingServiceImp implements ParkingService {
	
	@Autowired
	private ParkingDao parkingDao;
	
	@Override
	public Parking saveParking(Parking parking) {
		return parkingDao.save(parking);
	}

	@Override   //This is not the rule, just is a test. I need a Query
	//I need check status
	public List<Parking> findAllVehiclesParking(){
		return (List<Parking>)parkingDao.findAll();
	}
}
