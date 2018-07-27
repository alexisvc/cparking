package com.dna.cparking;

import static databuilder.ParkingBuilder.aParking;
import static databuilder.VehicleBuilder.aVehicle;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.util.EnumVehicleType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GatekeeperControllerTest {
	
	private static final int DISPLACEMENT = 180;
	private static final String PLATE = "XXX123";
	private static final String URL_HOME = "http://localhost:";
	private static final String URL_SERVICE_REGISTER = "/gatekeeper/registerEntry";
	private static final String URL_SERVICE_GIVE_OUT = "/gatekeeper/giveOut/";
	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@LocalServerPort
	private int localServerPort; 
	
	@Test
	public void registerEntryTest() {
		Vehicle vehicle = aVehicle()
				.withPlate(PLATE)
				.withDisplacement(DISPLACEMENT)
				.withVehicleType(EnumVehicleType.CAR)
				.build();
		
		ResponseEntity<Object> vehicleEntry = restTemplate.postForEntity ( URL_HOME + localServerPort + URL_SERVICE_REGISTER, vehicle, Object.class );
		
		System.out.println("******************************************************************** Port: " + localServerPort);
		
		assertEquals(HttpStatus.CREATED, vehicleEntry.getStatusCode());
	}
	
	@Test
	public void giveOutVehicle() {
		Calendar calendar = Calendar.getInstance();
		
		Parking parking = aParking()
				.withInDate(calendar.getTime())
				.withPayment(0)
				.withStatus(true)
				.with(aVehicle().withPlate(PLATE).withDisplacement(DISPLACEMENT).withVehicleType(EnumVehicleType.CAR))
				.build();		
		
		ResponseEntity<Object> vehicleGiveOut = restTemplate.postForEntity ( URL_HOME + localServerPort + URL_SERVICE_GIVE_OUT + parking.getVehicle().getPlate(), parking, Object.class );
		
		System.out.println("******************************************************************** Port: " + localServerPort);
		
		assertEquals(HttpStatus.ACCEPTED, vehicleGiveOut.getStatusCode());
	}
}
