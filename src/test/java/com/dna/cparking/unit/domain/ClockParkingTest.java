package com.dna.cparking.unit.domain;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.dna.cparking.domain.imp.ClockParkingImp;
import com.dna.cparking.domain.imp.TimerParking;

public class ClockParkingTest {
	
	private static final int NUMBER_MINUTES = 1880;

	@Test
	public void settingTimerParking() {
		TimerParking timerParking = null;
		ClockParkingImp clockParking = new ClockParkingImp();
				
		timerParking = clockParking.settingTimerParking(NUMBER_MINUTES);
				
		assertThat(timerParking, instanceOf(TimerParking.class));
	}

}
