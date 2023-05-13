package com.nsw.digital.vehicle.registration.persist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="TB_VEHICLE")
@Data

public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="VEHICLE_ID")
    private Long vehicleID;
    @Column(name="VEHICLE_REGO")
    private String rego;
    @Column(name="VEHICLE_MAKE")
    private String make;
    @Column(name="VEHICLE_MODEL")
    private String model;
    @Column(name="VEHICLE_YEAR")
    private int year;
    @Column(name="VEHICLE_COLOUR")
    private String colour;
    
    
	public VehicleEntity(Long vehicleID, String rego, String make, String model, int year, String colour) {
		super();
		this.vehicleID = vehicleID;
		this.rego = rego;
		this.make = make;
		this.model = model;
		this.year = year;
		this.colour = colour;
	}
	
	
	public VehicleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(Long vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getRego() {
		return rego;
	}
	public void setRego(String rego) {
		this.rego = rego;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
    
    
}
