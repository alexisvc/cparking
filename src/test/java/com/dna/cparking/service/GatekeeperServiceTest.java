package com.dna.cparking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dna.cparking.domain.CalendarParking;
import com.dna.cparking.exception.ExceptionParking;
import com.dna.cparking.message.CatalogMessages;
import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.model.dao.VehicleDao;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.util.EnumVehicleType;
import com.dna.cparking.util.ParamsConfigParking;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatekeeperServiceTest {
	
	@org.junit.Before
	public void mocksInitialization(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Autowired
	private GatekeeperService gatekeeperService;
	
	@MockBean
	private CalendarParking calendarParking;
	
	@MockBean
	private ParkingDao parkingDao;
	
	@MockBean
	private VehicleDao vehicleDao;
	
	private static final String PLATE_WITH_A = "ABC34A";
	
	@Test
	public void throwsExceptionWhenPlateWithAOnMondayOrSunday() {
		
		try {			
			Vehicle vehicle = new Vehicle();
			vehicle.setPlate(PLATE_WITH_A);
			vehicle.setVehicleType(EnumVehicleType.CAR);
			
			Mockito.when(calendarParking.isMondayOrSunday()).thenReturn(true);
			
			gatekeeperService.registerVehicleEntry(vehicle);
			
			fail();
		} catch (ExceptionParking e) {
			assertEquals(CatalogMessages.INVALID_PLATE_IN_DAY, e.getMessage());		
		}
	}
	
	@Test
	public void throwsExceptionWhenNotSpaceVehicleType() {
		
		try {			
			Vehicle vehicle = new Vehicle();
			vehicle.setPlate(PLATE_WITH_A);
			vehicle.setVehicleType(EnumVehicleType.CAR);
			
			Mockito.when(calendarParking.isMondayOrSunday()).thenReturn(false);
			Mockito.when(parkingDao.findAllVehicleInParkingByType(vehicle.getVehicleType())).thenReturn(ParamsConfigParking.MAX_CARS_ALLOWED + 1);
			
			gatekeeperService.registerVehicleEntry(vehicle);
			
			fail();
		} catch (ExceptionParking e) {
			assertEquals(CatalogMessages.THERE_IS_NOT_SPACE_FOR_VEHICLE_TYPE, e.getMessage());		
		}
	}
	
	@Test
	public void throwsExceptionWhenAlreadyVehicleIsParked() {
		
		try {			
			Vehicle vehicle = new Vehicle();
			vehicle.setPlate(PLATE_WITH_A);
			vehicle.setVehicleType(EnumVehicleType.CAR);
			
			Mockito.when(calendarParking.isMondayOrSunday()).thenReturn(false);
			Mockito.when(parkingDao.findAllVehicleInParkingByType(vehicle.getVehicleType())).thenReturn(ParamsConfigParking.MAX_CARS_ALLOWED - 1);
			Mockito.when(parkingDao.alreadyParked(vehicle.getPlate())).thenReturn(true);
			
			gatekeeperService.registerVehicleEntry(vehicle);
			
			fail();
		} catch (ExceptionParking e) {
			assertEquals(CatalogMessages.VEHICLE_ALREADY_IS_PARKED, e.getMessage());		
		}
	}
	
//	@Test
//	public void registerVehicleEntry() {
//		Vehicle vehicle = new Vehicle();
//		
//		vehicle.setPlate(PLATE_WITH_A);
//		vehicle.setVehicleType(EnumVehicleType.CAR);
//		
//		Mockito.when(calendarParking.isMondayOrSunday()).thenReturn(false);
//		Mockito.when(parkingDao.findAllVehicleInParkingByType(vehicle.getVehicleType())).thenReturn(ParamsConfigParking.MAX_CARS_ALLOWED - 1);
//		Mockito.when(parkingDao.alreadyParked(vehicle.getPlate())).thenReturn(false);
//		
//		
//		gatekeeperService.registerVehicleEntry(vehicle);
//	}
}
