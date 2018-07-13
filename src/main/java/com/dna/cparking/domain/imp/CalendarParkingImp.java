package com.dna.cparking.domain.imp;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.dna.cparking.domain.CalendarParking;

@Component
public class CalendarParkingImp implements CalendarParking{
	public boolean isMondayOrSunday() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) < 2;
	}
}
