package com.dna.cparking.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn (name = "ID_VEHICLE_TYPE", nullable = false)
	private VehicleType vehicleType;

	public Vehicle(String plate, int displacement, VehicleType vehicleType) {
		super();
		this.plate = plate;
		this.displacement = displacement;
		this.vehicleType = vehicleType;
	}
}
