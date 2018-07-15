package com.dna.cparking.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dna.cparking.util.EnumVehicleType;

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
	
	@Enumerated(EnumType.STRING)
	@Column (name = "VEHICLE_TYPE", nullable = false)
	private EnumVehicleType vehicleType;
	
	public Vehicle() {
		super();
	}

	public Vehicle(String plate, int displacement, EnumVehicleType vehicleType) {
		super();
		this.plate = plate;
		this.displacement = displacement;
		this.vehicleType = vehicleType;
	}
	
	public int getidVehicle() {
		return idVehicle;
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

	public EnumVehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(EnumVehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
}
