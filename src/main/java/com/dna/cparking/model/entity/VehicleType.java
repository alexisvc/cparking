package com.dna.cparking.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "VEHICLE_TYPE")
public class VehicleType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "ID_VEHICLE_TYPE", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idVehicleType;
	
	@Column (name = "DESCRIPTION", nullable = false)
	private String description;

	public VehicleType(String description) {
		super();
		this.description = description;
	}
}
