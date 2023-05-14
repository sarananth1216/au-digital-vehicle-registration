package com.nsw.digital.vehicle.registration.controller;

import com.nsw.digital.vehicle.registration.exception.VehicleNotFoundException;
import com.nsw.digital.vehicle.registration.model.VehicleRequest;
import com.nsw.digital.vehicle.registration.model.VehicleResponse;
import com.nsw.digital.vehicle.registration.persist.dto.VehicleEntity;
import com.nsw.digital.vehicle.registration.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/addVehicle")
    public ResponseEntity<VehicleResponse> saveVehicle(@RequestBody @Valid VehicleRequest vehicleRequest){
        return new ResponseEntity<VehicleResponse>(vehicleService.saveVehicle(vehicleRequest), HttpStatus.CREATED);

    }

    @GetMapping("/getAllVehicles")
    public ResponseEntity<List<VehicleEntity>> getAllVehicles(){
        List<VehicleEntity> vehicleEntityList = vehicleService.getAllVehicles();
        if(vehicleEntityList.isEmpty() || vehicleEntityList.size()==0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<VehicleEntity>>(vehicleEntityList,HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleEntity> getVehicleById(@PathVariable Long vehicleId) throws VehicleNotFoundException {

            return new ResponseEntity<>(vehicleService.findByVehicleID(vehicleId), HttpStatus.OK);

    }
}
