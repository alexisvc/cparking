package com.dna.cparking.util;

public final class ParamsConfigParking {
	
	private ParamsConfigParking() {	}	
	
	public static final int MAX_CARS_ALLOWED= 20;
	public static final int MAX_MOTORBIKES_ALLOWED = 10;
	public static final String RESTRICTION_INITIAL_LETTER = "A";
	
	public static final double VALUE_HOUR_CAR = 1000;
	public static final double VALUE_DAY_CAR = 8000;
	public static final double VALUE_HOUR_MOTORBIKE = 500;
	public static final double VALUE_DAY_MOTORBIKE = 4000;
	public static final double VALUE_SURCHARGE_MOTORBIKE_500CC = 4000;
}
