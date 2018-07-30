package com.dna.cparking.unit.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dna.cparking.domain.ClockParking;
import com.dna.cparking.domain.Gatekeeper;
import com.dna.cparking.domain.imp.TimerParking;
import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.util.EnumVehicleType;
import com.dna.cparking.util.ParamsConfigParking;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GatekeeperTest {
	
	@org.junit.Before
	public void mocksInitialization(){
		MockitoAnnotations.initMocks(this);
	}
	
	private static final String PLATE_WITH_A = "ABC34A";
	private static final String PLATE_WITHOUT_A = "CBA34S";
	private static final String PLATE_PARKED = "XTS55D";
	private static final String PLATE_UNPARKED = "OIU33A";
	
	private static final int EXPECTED_PAYMENT_CAR = 8000;
	private static final int DISPLACEMENT_CAR = 1200;	
	private static final int EXPECTED_PAYMENT_MOTORBIKE = 4000;
	private static final int DISPLACEMENT_MOTORBIKE = 150;	
	private static final int EXPECTED_PAYMENT_MOTORBIKE_500CC= 6000;
	private static final int DISPLACEMENT_MOTORBIKE_500CC = 800;
	private static final int MORE_HOURS_ON_TIME_PARKING = 11;
	
//	private Vehicle carToTest = new Vehicle(PLATE_TO_TEST, 1200, EnumVehicleType.CAR);
	
	private boolean response;
	
	@Autowired
	private Gatekeeper gatekeeper;

	@MockBean
	private ParkingDao parkingDao;
	
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
	
	@Test
	public void parkingIsEmpty() {	
		
		Mockito.when(parkingDao.parkingIsEmpty()).thenReturn(true);		
		response = gatekeeper.checkParkingIsEmpty();
		
		assertTrue(response);		
	}
	
//	@Test
//	public void getParkingToGiveOutVehicle() {
//		Calendar calendar = Calendar.getInstance();
//		Date date = calendar.getTime();		
//		Parking expectedParking = new Parking(date, date, true, carToTest, 0);		
//		Parking dummyParking = new Parking(date, date, true, carToTest, 0);
//		
//		Mockito.when(parkingDao.findVehicleInParkingByPlate(carToTest.getPlate())).thenReturn(dummyParking);
//		dummyParking = gatekeeper.getParkingToGiveOutVehicle(carToTest.getPlate());
//		
//		assertEquals(expectedParking, dummyParking);
//		
//	}

	@Test
	public void calculatePayment() {
		int payment = 0;
		ClockParking clock = mock(ClockParking.class);
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		
		calendar.add(Calendar.HOUR, MORE_HOURS_ON_TIME_PARKING);
		Date later = calendar.getTime();
			
		Mockito.when(clock.settingTimerParking(540)).thenReturn(new TimerParking(1, 1));
		
		payment = gatekeeper.calculatePayment(now, later, ParamsConfigParking.VALUE_DAY_CAR, ParamsConfigParking.VALUE_HOUR_CAR);
		
		assertEquals(EXPECTED_PAYMENT_CAR, payment);
	}
	
	@Test
	public void generatePaymentToCar() {
		Gatekeeper spyGatekeeper = Mockito.spy(gatekeeper);
		int payment = 0;
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		
		calendar.add(Calendar.HOUR, MORE_HOURS_ON_TIME_PARKING);
		Date later = calendar.getTime();
		
		Mockito.when(spyGatekeeper.calculatePayment(now, later, ParamsConfigParking.VALUE_DAY_CAR, ParamsConfigParking.VALUE_HOUR_CAR)).thenReturn(EXPECTED_PAYMENT_CAR);
				
		payment = gatekeeper.generatePayment(EnumVehicleType.CAR, now, later, DISPLACEMENT_CAR);
		
		assertEquals(EXPECTED_PAYMENT_CAR, payment);		
	}
	
	@Test
	public void generatePaymentToMotorbike() {
		Gatekeeper spyGatekeeper = Mockito.spy(gatekeeper);
		int payment = 0;
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		
		calendar.add(Calendar.HOUR, MORE_HOURS_ON_TIME_PARKING);
		Date later = calendar.getTime();
		
		Mockito.when(spyGatekeeper.calculatePayment(now, later, ParamsConfigParking.VALUE_DAY_MOTORBIKE, ParamsConfigParking.VALUE_HOUR_MOTORBIKE)).thenReturn(EXPECTED_PAYMENT_MOTORBIKE);
				
		payment = gatekeeper.generatePayment(EnumVehicleType.MOTORBIKE, now, later, DISPLACEMENT_MOTORBIKE);
		
		assertEquals(EXPECTED_PAYMENT_MOTORBIKE, payment);		
	}
	
	@Test
	public void generatePaymentToMotorbike500CC() {
		Gatekeeper spyGatekeeper = Mockito.spy(gatekeeper);
		int payment = 0;
		
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		
		calendar.add(Calendar.HOUR, MORE_HOURS_ON_TIME_PARKING);
		Date later = calendar.getTime();
		
		Mockito.when(spyGatekeeper.calculatePayment(now, later, ParamsConfigParking.VALUE_DAY_MOTORBIKE, ParamsConfigParking.VALUE_HOUR_MOTORBIKE)).thenReturn(EXPECTED_PAYMENT_MOTORBIKE);
				
		payment = gatekeeper.generatePayment(EnumVehicleType.MOTORBIKE, now, later, DISPLACEMENT_MOTORBIKE_500CC);
		
		assertEquals(EXPECTED_PAYMENT_MOTORBIKE_500CC, payment);		
	}	
}
