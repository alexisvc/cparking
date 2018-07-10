package com.dna.cparking.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "RATE")
public class Rate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "ID_RATE", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idRate;
	
	@Column (name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column (name = "DAY_VALUE", nullable = false)
	private double dayValue;
	
	@Column (name = "HOUR_VALUE", nullable = false)
	private double hourValue;
	
	@Column (name = "SURCHARGE", nullable = false)
	private double surcharge;
	
	public Rate(String description, double dayValue, double hourValue, double surcharge) {
		super();
		this.description = description;
		this.dayValue = dayValue;
		this.hourValue = hourValue;
		this.surcharge = surcharge;
	}
	
		
}
