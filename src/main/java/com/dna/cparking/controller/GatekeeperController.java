package com.dna.cparking.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dna.cparking.exception.ApiErrorBuilderException;
import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.service.GatekeeperService;


@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/gatekeeper")
public class GatekeeperController {
	
	@Autowired
	private GatekeeperService gatekeeperService;

	@RequestMapping(value = "/registerEntry", method = RequestMethod.POST)	
	public ResponseEntity<Object> enterVehicleInParkig(@RequestBody Vehicle vehicle) throws ApiErrorBuilderException {
		
		gatekeeperService.registerVehicleEntry(vehicle);
		
		return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/giveOut/{plate}", method = RequestMethod.PUT)
	public ResponseEntity<Object> giveOutVehicleInParking(@PathVariable("plate") String plate) throws ApiErrorBuilderException{
		
		Parking parking = gatekeeperService.giveOutVehicle(plate);
		
		return new ResponseEntity<>(parking, HttpStatus.ACCEPTED);
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/findAllParked", method = RequestMethod.GET )
	public ResponseEntity<Object> findAllVehicleParked() throws ApiErrorBuilderException {
		
		List<Parking> parking = gatekeeperService.findAllVehicles();

		return new ResponseEntity<>(parking, HttpStatus.OK);
	}
}
