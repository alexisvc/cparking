package com.dna.cparking.message;

public class CatalogMessages {
	
	CatalogMessages(){}
	
	public static final String INVALID_PLATE_IN_DAY = "El vehículo no está autorizado para ingresar el día de hoy. Tiene restricción por su placa.";
	public static final String THERE_IS_NOT_SPACE_FOR_VEHICLE_TYPE = "No hay celdas disponibles para el tipo de vehículo que intenta ingresar.";
	public static final String VEHICLE_ALREADY_IS_PARKED = "El vehículo que intenta estacionar ya se encuentra dentro del parqueadero.";
	public static final String VEHICLE_IS_NOT_PARKED = "El vehículo que indica no se encuentra dentro del parqueadero.";
	public static final String PARKING_IS_EMPTY = "El parqueadero se encuentra vacío.";

}