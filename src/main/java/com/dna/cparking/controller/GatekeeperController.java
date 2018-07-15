package com.dna.cparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.service.GatekeeperService;


@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/cparking")
public class GatekeeperController {
	
	@Autowired
	private GatekeeperService gatekeeperService;
	
	@RequestMapping(value = "/gatekeeper", method = RequestMethod.POST)	
	public void createParking(@RequestBody Vehicle vehicle) {		
		gatekeeperService.enterVehicle(vehicle);
	}	
}
