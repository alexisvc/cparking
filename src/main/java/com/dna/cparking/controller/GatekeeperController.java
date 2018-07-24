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

import com.dna.cparking.exception.ExceptionParking;
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
	public ResponseEntity<Object> enterVehicleInParkig(@RequestBody Vehicle vehicle) {
		try {
			gatekeeperService.registerVehicleEntry(vehicle);
		} catch (ExceptionParking e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<>(vehicle, HttpStatus.OK);
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/giveOut/{plate}", method = RequestMethod.PUT)
	public ResponseEntity<Object> giveOutVehicleInParking(@PathVariable("plate") String plate) {
		Parking parking;
		try {			
			parking = gatekeeperService.giveOutVehicle(plate);
		} catch (ExceptionParking e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}	
		return new ResponseEntity<>(parking, HttpStatus.OK);
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/findAllParked", method = RequestMethod.GET )
	public ResponseEntity<Object> findAllVehicleParked() {
		List<Parking> parking;
		try {
			parking = gatekeeperService.findAllVehicles();
		} catch (ExceptionParking e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		
		}
		return new ResponseEntity<>(parking, HttpStatus.OK);
	}
}
