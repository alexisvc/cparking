package com.dna.cparking.domain;

import java.util.Date;

import com.dna.cparking.domain.imp.TimerParking;

public interface ClockParking {
	public int getMinutesInParking(Date inDate, Date outDate);
	public TimerParking settingTimerParking(int minutesInParking);
}
