package com.dna.cparking.service.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.service.ParkingService;

@Service
public class ParkingServiceImp implements ParkingService {
	
	@Autowired
	private ParkingDao parkingDao;
	
	@Override
	@Transactional
	public void saveParking(Parking parking) {
		parkingDao.save(parking);
	}
	
	@Override
	public Parking settingParking (Parking parking, Vehicle vehicle) {
		parking.setVehicle(vehicle);
		parking.setInDate(Calendar.getInstance().getTime());
		parking.setOutDate(null);
		parking.setPayment(0);
		parking.setStatus(true);
		
		return parking;
	}
	
	@Override
	public Parking getParkingToGiveOut(String plate) {
		return parkingDao.findVehicleInParkingByPlate(plate);
	}
	
	@Override
	@Transactional
	public Parking parkingGiveOutById(Parking parking, Date outDate, int payment) {
		
		parking.setOutDate(outDate);
		parking.setPayment(payment);
		parking.setStatus(false);
		
		saveParking(parking);
		
		return parking;
	}
	
	@Override
	public List<Parking> findAllParking(){
		return parkingDao.findAllVehiclesInParking();
	}
}
