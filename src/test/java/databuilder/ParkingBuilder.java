package databuilder;

import java.util.Date;

import com.dna.cparking.model.entity.Parking;
import com.dna.cparking.model.entity.Vehicle;

public class ParkingBuilder {
	
	private Date inDate;
	private Date outDate;
	private boolean status;
	private int payment;
	private Vehicle vehicle;
	
	public ParkingBuilder withInDate (Date inDate){
		this.inDate = inDate;
		return this;
	}
	
	public ParkingBuilder withOutDate (Date outDate){
		this.outDate = outDate;
		return this;
	}
	
	public ParkingBuilder withStatus (boolean status){
		this.status = status;
		return this;
	}
	
	public ParkingBuilder withPayment (int payment){
		this.payment = payment;
		return this;
	}
	
	public Parking build(){
        return new Parking(this.inDate, this.outDate, this.status, this.payment, this.vehicle);
    }
	
	public static ParkingBuilder aParking(){
        return new ParkingBuilder();
    }
	
	public ParkingBuilder with(VehicleBuilder vehicleBuilder) {
		vehicle = vehicleBuilder.build();
		return this;
	}
}
