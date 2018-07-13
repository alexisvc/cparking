package com.dna.cparking.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "VEHICLE")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "ID_VEHICLE", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idVehicle;
	
	@Column (name = "PLATE", nullable = false)
	private String plate;
	
	@Column (name = "DISPLACEMENT", nullable = false)
	private int displacement;
	
	@Column (name = "VEHICLE_TYPE", nullable = false)
	private String vehicleType;
	
	public Vehicle() {
		super();
	}

	public Vehicle(String plate, int displacement, String vehicleType) {
		super();
		this.plate = plate;
		this.displacement = displacement;
		this.vehicleType = vehicleType;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public int getDisplacement() {
		return displacement;
	}

	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
