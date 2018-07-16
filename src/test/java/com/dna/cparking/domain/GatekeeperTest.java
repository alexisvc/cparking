package com.dna.cparking.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.dna.cparking.domain.imp.GatekeeperImp;
import com.dna.cparking.model.dao.ParkingDao;
import com.dna.cparking.util.EnumVehicleType;
import com.dna.cparking.util.ParamsConfigParking;

public class GatekeeperTest {
	
	private static final String PLATE_WITH_A = "ABC34A";
	private static final String PLATE_WITHOUT_A = "CBA34S";
	
	@Test
	public void plateStarWithA() {
//	Arrange	
		Gatekeeper gatekeeper = new GatekeeperImp();
		boolean response;
//	Act
		response = gatekeeper.checkPlateStartWithA(PLATE_WITH_A);
//	Assert
		assertTrue(response);
	}
		
	@Test
	public void plateStartWithoutA() {
//	Arrange	
		Gatekeeper gatekeeper = new GatekeeperImp();
		boolean response;
//	Act
		response = gatekeeper.checkPlateStartWithA(PLATE_WITHOUT_A);
//	Assert
		assertFalse(response);
	}
	
//	@Test
//	public void ThereIsSpaceForMotorbike() {
////	Arrange
//		Gatekeeper gatekeeper = new GatekeeperImp();
//		ParkingDao parkingDao = mock(ParkingDao.class);
//		Mockito.when(parkingDao.findAllVehicleInParkingByType(EnumVehicleType.MOTORBIKE)).thenReturn(ParamsConfigParking.MAX_MOTORBIKES_ALLOWED - 2);
//		boolean response;
////	Act
//		response = gatekeeper.checkSpaceVehicleType(EnumVehicleType.MOTORBIKE);
////	Assert
//		assertTrue(response);
//	}
}
