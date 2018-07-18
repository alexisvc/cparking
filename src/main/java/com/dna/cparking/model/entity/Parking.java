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
	
	@Column (name = "IN_DATE")
	private Date inDate;
	
	@Column (name = "OUT_DATE")
	private Date outDate;
	
	@Column (name = "STATUS", nullable = false)
	private boolean status;

	@Column (name = "PAYMENT", nullable = false)
	private int payment;
	
	@ManyToOne
	@JoinColumn (name = "ID_VEHICLE", nullable = false)
	private Vehicle vehicle;
	
	public Parking() {
		super();
	}	
	
	public Parking(Date inDate, Date outDate, boolean status, Vehicle vehicle, int payment) {
		super();
		this.inDate = inDate;
		this.outDate = outDate;
		this.status = status;
		this.vehicle = vehicle;
		this.payment = payment;
	}
	
	public int getidParking() {
		return idParking;
	}
	
	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}
}
