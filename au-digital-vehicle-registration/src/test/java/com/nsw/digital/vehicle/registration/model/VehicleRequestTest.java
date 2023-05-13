package com.nsw.digital.vehicle.registration.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VehicleRequestTest {

	@InjectMocks
	private VehicleRequest vehicleRequestTest;
	
	@BeforeEach
	public void InitializeValues() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void requestTest() {
		vehicleRequestTest.setColour("black");
		vehicleRequestTest.setMake("Honda");
		vehicleRequestTest.setModel("Accord");
		vehicleRequestTest.setRego("1111");

		
		assertNotNull(vehicleRequestTest.getColour());
		assertNotNull(vehicleRequestTest.getMake());
		assertNotNull(vehicleRequestTest.getModel());
		assertNotNull(vehicleRequestTest.getRego());

		
	}
}
