package com.nsw.digital.vehicle.registration.exception;

public class VehicleAlreadyExistsException extends Exception{
    public VehicleAlreadyExistsException(String message){
        super(message);
    }
}
