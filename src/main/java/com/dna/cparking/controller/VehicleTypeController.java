package com.dna.cparking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dna.cparking.model.entity.VehicleType;
import com.dna.cparking.service.VehicleTypeService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/cparking")
public class VehicleTypeController {
	
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	@RequestMapping(value = "/vehicleType", method = RequestMethod.POST)	
	public VehicleType create(@RequestBody VehicleType vehicleType) {
		return vehicleTypeService.saveVehicleType(vehicleType);
	}

}
