package com.dna.cparking.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.util.EnumVehicleType;
import com.dna.cparking.util.ParamsConfigParking;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GatekeeperTest {
	
	private static final String PLATE_WITH_A = "ABC34A";
	private static final String PLATE_WITHOUT_A = "CBA34S";
	private static final String PLATE_PARKED = "XTS55D";
	private static final String PLATE_UNPARKED = "OIU33A";
	
	private boolean response;
	
	@Autowired
	private Gatekeeper gatekeeper;

	@MockBean
	private ParkingDao parkingDao;
	
	@org.junit.Before
	public void mocksInitialization(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void plateStarWithA() {
		response = gatekeeper.checkPlateStartWithA(PLATE_WITH_A);
		
		assertTrue(response);
	}
		
	@Test
	public void plateStartWithoutA() {		
		response = gatekeeper.checkPlateStartWithA(PLATE_WITHOUT_A);
		
		assertFalse(response);
	}
	
	@Test
	public void thereIsSpaceForMotorbike() {
		Mockito.when(parkingDao.findAllVehicleInParkingByType(EnumVehicleType.MOTORBIKE)).thenReturn(ParamsConfigParking.MAX_MOTORBIKES_ALLOWED - 2);
		
		response = gatekeeper.checkSpaceVehicleType(EnumVehicleType.MOTORBIKE);
		
		assertTrue(response);
	}
	
	@Test
	public void thereIsSpaceForCar() {
		Mockito.when(parkingDao.findAllVehicleInParkingByType(EnumVehicleType.CAR)).thenReturn(ParamsConfigParking.MAX_CARS_ALLOWED - 2);
		
		response = gatekeeper.checkSpaceVehicleType(EnumVehicleType.CAR);
		
		assertTrue(response);
	}
	
	@Test
	public void thereIsNotSpaceForMotorbike() {
		Mockito.when(parkingDao.findAllVehicleInParkingByType(EnumVehicleType.MOTORBIKE)).thenReturn(ParamsConfigParking.MAX_MOTORBIKES_ALLOWED + 2);
		
		response = gatekeeper.checkSpaceVehicleType(EnumVehicleType.MOTORBIKE);
		
		assertFalse(response);
	}
	
	@Test
	public void thereIsNotSpaceForCar() {
		Mockito.when(parkingDao.findAllVehicleInParkingByType(EnumVehicleType.CAR)).thenReturn(ParamsConfigParking.MAX_CARS_ALLOWED + 2);
		
		response = gatekeeper.checkSpaceVehicleType(EnumVehicleType.CAR);
		
		assertFalse(response);
	}
	
	@Test
	public void vehicleAlreadyParked() {
		Mockito.when(parkingDao.alreadyParked(PLATE_PARKED)).thenReturn(true);
		
		response = gatekeeper.checkVehicleIsParked(PLATE_PARKED);
		
		assertTrue(response);
	}
	
	@Test
	public void vehicleIsNotParked() {
		Mockito.when(parkingDao.alreadyParked(PLATE_UNPARKED)).thenReturn(false);
		
		response = gatekeeper.checkVehicleIsParked(PLATE_UNPARKED);
		
		assertFalse(response);
		
	}
}
