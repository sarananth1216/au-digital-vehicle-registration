package com.nsw.digital.vehicle.registration.service;

import com.nsw.digital.vehicle.registration.exception.VehicleAlreadyExistsException;
import com.nsw.digital.vehicle.registration.exception.VehicleNotFoundException;
import com.nsw.digital.vehicle.registration.model.VehicleRequest;
import com.nsw.digital.vehicle.registration.model.VehicleResponse;
import com.nsw.digital.vehicle.registration.persist.dto.VehicleEntity;

import java.util.List;


public interface VehicleService {

    VehicleResponse saveVehicle(VehicleRequest vehicleRequest) throws VehicleAlreadyExistsException;
    List<VehicleEntity> getAllVehicles();
    VehicleEntity findByVehicleID(Long id) throws VehicleNotFoundException;


}
