package com.dna.cparking.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "PARKING")
public class Parking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "ID_PARKING", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idParking;
	
	@Column (name = "IN_DATE", nullable = false)
	private Date inDate;
	
	@Column (name = "OUT_DATE", nullable = false)
	private Date outDate;
	
	@Column (name = "STATUS", nullable = false)
	private boolean status;
	
	@ManyToOne
	@JoinColumn (name = "ID_VEHICLE" )
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn (name = "ID_RATE" )
	private Rate rate;

	public Parking(Date inDate, Date outDate, boolean status, Vehicle vehicle, Rate rate) {
		super();
		this.inDate = inDate;
		this.outDate = outDate;
		this.status = status;
		this.vehicle = vehicle;
		this.rate = rate;
	}
	
	
}
