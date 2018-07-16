package com.dna.cparking.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.service.ParkingService;

@Service
public class ParkingServiceImp implements ParkingService {
	
	@Autowired
	private ParkingDao parkingDao;
	
//	Save a parking data on database.
	@Override
	@Transactional
	public void saveParking(Parking parking) {
		parkingDao.save(parking);
	}
	
//	Return integer with the number of vehicles in parking.
	@Override
	public List<Parking> findAllParking(){
		return parkingDao.findAllVehicleInParking();
	}
}
