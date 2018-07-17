package com.dna.cparking.domain.imp;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.dna.cparking.domain.ClockParking;
import com.dna.cparking.domain.TimerParking;
import com.dna.cparking.util.ParamsConfigParking;

@Component
public class ClockParkingImp implements ClockParking {
	
	private static final int MILISECONDS = 60000; 

	//	Could be better
	public int getMinutesInParking(Date inDate, Date outDate) {
		return (int) ((outDate.getTime() - inDate.getTime()) / MILISECONDS);
	}
	
	public TimerParking settingTimerParking(int minutesInParking){
		
		int days = (minutesInParking / 60) / 24;
		int hours = (minutesInParking / 60) % 24;
		int minutes = (minutesInParking % 60);
		
		if (hours > ParamsConfigParking.NUM_HOURS_IS_DAY) {
			days++;
			hours = 0;
		}
		
		if (minutes > 0) {
			hours++;
		}
		
		return new TimerParking(days, hours);
	}
}
