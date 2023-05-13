package com.nsw.digital.vehicle.registration.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class VehicleResponseTest {

	@InjectMocks
	private VehicleResponse vehicleResponseTest;
	
	@BeforeEach
	public void InitializeValues() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void responseTest() {
		vehicleResponseTest.setId(Long.valueOf(100));
		vehicleResponseTest.setStatus("100");

		
		assertNotNull(vehicleResponseTest.getId());
		assertNotNull(vehicleResponseTest.getStatus());


		
	}
}
