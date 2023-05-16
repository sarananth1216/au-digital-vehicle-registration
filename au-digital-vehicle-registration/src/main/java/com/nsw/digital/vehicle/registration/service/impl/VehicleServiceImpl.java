package com.nsw.digital.vehicle.registration.service.impl;

import com.nsw.digital.vehicle.registration.constants.SaveStatus;
import com.nsw.digital.vehicle.registration.exception.VehicleAlreadyExistsException;
import com.nsw.digital.vehicle.registration.exception.VehicleNotFoundException;
import com.nsw.digital.vehicle.registration.model.VehicleRequest;
import com.nsw.digital.vehicle.registration.model.VehicleResponse;
import com.nsw.digital.vehicle.registration.persist.dto.VehicleEntity;
import com.nsw.digital.vehicle.registration.persist.repo.VehicleRepo;
import com.nsw.digital.vehicle.registration.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Override
    public VehicleResponse saveVehicle(VehicleRequest vehicleRequest) throws VehicleAlreadyExistsException {

        VehicleEntity savedVehicle=null;
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setMake(vehicleRequest.getMake());
        vehicleEntity.setYear(vehicleRequest.getYear());
        vehicleEntity.setRego(vehicleRequest.getRego());
        vehicleEntity.setModel(vehicleRequest.getModel());
        vehicleEntity.setColour(vehicleRequest.getColour());

        VehicleEntity regoVehicle = vehicleRepo.findByRego(vehicleRequest.getRego());
        if(regoVehicle !=null) {
            throw new VehicleAlreadyExistsException(regoVehicle.getRego() + " already exists");
        }
        else {
            savedVehicle = vehicleRepo.save(vehicleEntity);
        }
        return new VehicleResponse(savedVehicle.getVehicleID(),SaveStatus.SAVE_SUCCESS);

    }

    @Override
    public List<VehicleEntity> getAllVehicles() {
        List<VehicleEntity> vehicleEntityList = (List<VehicleEntity>) vehicleRepo.findAll();
        return vehicleEntityList;
    }

    @Override
    public VehicleEntity findByVehicleID(Long id) throws VehicleNotFoundException {
        Optional<VehicleEntity> vehicleById = vehicleRepo.findById(id);
        if(vehicleById.isPresent())
            return vehicleById.get();
        else
            throw new VehicleNotFoundException("Vehicle information not found for ID: " + id);

    }
}
