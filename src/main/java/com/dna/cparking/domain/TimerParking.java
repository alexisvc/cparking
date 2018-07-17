package com.dna.cparking.domain;

public class TimerParking {
	private int days;
	private int hours;
	
	public TimerParking(int days, int hours) {
		super();
		this.days = days;
		this.hours = hours;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getDays() {
		return days;
	}

	public int getHours() {
		return hours;
	}
}
