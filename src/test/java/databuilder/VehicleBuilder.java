package databuilder;

import com.dna.cparking.model.entity.Vehicle;
import com.dna.cparking.util.EnumVehicleType;

public class VehicleBuilder {

	private String plate;
	private int displacement;
	private EnumVehicleType vehicleType;
	
	public VehicleBuilder() {
		this.plate = "XXX-XXX";
		this.displacement = 0;
		this.vehicleType = EnumVehicleType.CAR;
	}	
	
	public VehicleBuilder withPlate (String plate){
		this.plate = plate;
		return this;
	}
	
	public VehicleBuilder withDisplacement (int displacement){
		this.displacement = displacement;
		return this;
	}
	
	public VehicleBuilder withVehicleType (EnumVehicleType vehicleType){
		this.vehicleType = vehicleType;
		return this;
	}
	
	public Vehicle build(){
        return new Vehicle(this.plate, this.displacement, this.vehicleType);
    }
	
	public static VehicleBuilder aVehicle(){
        return new VehicleBuilder();
    }	
}
