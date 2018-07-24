package com.dna.cparking.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dna.cparking.domain.CalendarParking;
import com.dna.cparking.domain.Gatekeeper;
import com.dna.cparking.exception.ExceptionParking;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.util.EnumVehicleType;

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
	private Gatekeeper gatekeeper;
	
	@MockBean
	private CalendarParking calendarParking;
	
	private static final String PLATE_WITH_A = "ABC34A";

	@Test(expected = ExceptionParking.class)
	public void throwsExceptionWhenPlateWithAOnMondayOrSunday() {
		Vehicle vehicle = mock(Vehicle.class);
		
		Mockito.when(calendarParking.isMondayOrSunday()).thenReturn(true);
		Mockito.when(gatekeeper.checkPlateStartWithA(PLATE_WITH_A)).thenReturn(true);
		Mockito.when(gatekeeper.checkSpaceVehicleType(EnumVehicleType.CAR)).thenReturn(true);
		Mockito.when(gatekeeper.checkVehicleIsParked(PLATE_WITH_A)).thenReturn(false);
		
		gatekeeperService.registerVehicleEntry(vehicle);
	}
}
