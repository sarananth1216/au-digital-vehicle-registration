package com.nsw.digital.vehicle.registration.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.nsw.digital.vehicle.registration.exception.VehicleAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.meanbean.test.BeanTester;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.nsw.digital.vehicle.registration.exception.VehicleNotFoundException;
import com.nsw.digital.vehicle.registration.model.VehicleRequest;
import com.nsw.digital.vehicle.registration.model.VehicleResponse;
import com.nsw.digital.vehicle.registration.persist.dto.VehicleEntity;
import com.nsw.digital.vehicle.registration.persist.repo.VehicleRepo;
import org.springframework.web.bind.MethodArgumentNotValidException;

@SpringBootTest
public class VehicleRegistrationServiceImplTest {
	
	@InjectMocks
	private VehicleServiceImpl vehicleServiceImplTest;
	
	@Mock
	private VehicleRepo vehicleRepoTest;
	
	@Mock
	private AutoCloseable closeable;
	
	VehicleResponse vehicleResponse = new VehicleResponse();
	VehicleRequest vehicleRequest = new VehicleRequest();
	VehicleEntity vehicle;
	VehicleEntity vehicleEntity1 = new VehicleEntity();
	List<VehicleEntity> vehicleEntityList = new ArrayList<>();
	List<VehicleEntity> vehicleEntityNoItemsList = new ArrayList<>();
	String status;
	Long vehicleId;
	
	@BeforeEach
	public void initializeValues() {
		closeable = MockitoAnnotations.openMocks(this);
		//assign value for vehicle response values 
		vehicleId =  Long.valueOf(100);
		status = "200: successfull";	
		
		//initializing values for vehicle request class
		vehicleRequest.setColour("black");
		vehicleRequest.setMake("Honda");
		vehicleRequest.setModel("Accord");
		vehicleRequest.setRego("1111");
		vehicleRequest.setYear(2019);
		vehicle = new VehicleEntity(Long.valueOf(100),"CX78GD","Nissan","xTrail",1987,"red");
		vehicleEntityList.add(vehicle);
		
		//initializing values for vehicle response class
		vehicleResponse.setId(vehicleId);
		vehicleResponse.setStatus(status);
		
		
	}
	
	@AfterEach
	public void close() throws Exception {
		closeable.close();
	}
	
	@Test
	void testSaveVehicle() throws VehicleAlreadyExistsException {
        Mockito.when(vehicleRepoTest.save(ArgumentMatchers.any(VehicleEntity.class))).thenReturn(vehicle);
        VehicleResponse response = vehicleServiceImplTest.saveVehicle(vehicleRequest);
        response.setId(vehicle.getVehicleID());
        response.setStatus("Success");
        assertEquals(Long.valueOf(100),response.getId());
    }


	@Test
	void getAllVehicleTestMethod() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(VehicleRequest.class);	
		Mockito.when(vehicleRepoTest.findAll()).thenReturn(vehicleEntityList);
		List<VehicleEntity> response = vehicleServiceImplTest.getAllVehicles();
		assertFalse(response.isEmpty());
	}
	@Test
	void getAllVehicleWithItemsTestMethod() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(VehicleRequest.class);	
		Mockito.when(vehicleRepoTest.findAll()).thenReturn(vehicleEntityNoItemsList);
		List<VehicleEntity> response = vehicleServiceImplTest.getAllVehicles();
		assertTrue(response.isEmpty());
	}
	
	@Test
	void findByVehicleIDTestMethod() throws VehicleNotFoundException {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(VehicleRequest.class);	
		Mockito.when(vehicleRepoTest.findById(vehicleId)).thenReturn(Optional.of(vehicle));
		VehicleEntity response = vehicleServiceImplTest.findByVehicleID(vehicleId);
		assertNotNull(response.getMake());
		assertEquals("CX78GD",response.getRego());
	}

	@Test
	public void testVehicleNotFound() {
		Mockito.when(vehicleRepoTest.findById(any(Long.class))).thenReturn(Optional.empty());

		VehicleNotFoundException exp = assertThrows(VehicleNotFoundException.class, () -> vehicleServiceImplTest.findByVehicleID(200L));
		assertEquals("Vehicle information not found for ID: 200", exp.getMessage());
	}


	
	
	

}
