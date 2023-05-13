package com.nsw.digital.vehicle.registration.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.nsw.digital.vehicle.registration.persist.dto.VehicleEntity;

public class VehicleDtoTest {

	@InjectMocks
	private VehicleEntity vehicleEntityTest;
	
	@BeforeEach
	public void InitializeValues() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void vehicleEntityTest() {
		vehicleEntityTest.setColour("black");
		vehicleEntityTest.setMake("Honda");
		vehicleEntityTest.setModel("Accord");
		vehicleEntityTest.setRego("1111");
		vehicleEntityTest.setVehicleID(Long.valueOf(100));
		
		assertNotNull(vehicleEntityTest.getColour());
		assertNotNull(vehicleEntityTest.getMake());
		assertNotNull(vehicleEntityTest.getModel());
		assertNotNull(vehicleEntityTest.getRego());
		assertNotNull(vehicleEntityTest.getVehicleID());
		
	}
}
