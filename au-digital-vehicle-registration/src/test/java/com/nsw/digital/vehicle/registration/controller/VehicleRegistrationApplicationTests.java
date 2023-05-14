package com.nsw.digital.vehicle.registration.controller;

import com.nsw.digital.vehicle.registration.exception.VehicleNotFoundException;
import com.nsw.digital.vehicle.registration.model.VehicleRequest;
import com.nsw.digital.vehicle.registration.model.VehicleResponse;
import com.nsw.digital.vehicle.registration.persist.dto.VehicleEntity;
import com.nsw.digital.vehicle.registration.service.VehicleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class VehicleRegistrationApplicationTests {

	@InjectMocks
	private VehicleController vehicleControllerTest;
	
	@Mock
	private VehicleService vehicleServiceTest;
	
	@Mock
	private AutoCloseable closeable;
	
	VehicleResponse vehicleResponse = new VehicleResponse();
	VehicleRequest vehicleRequest = new VehicleRequest();
	VehicleEntity vehicleEntity = new VehicleEntity();
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
	
		
		//initializing values for vehicle response class
		vehicleResponse.setId(vehicleId);
		vehicleResponse.setStatus(status);
		
		//initializing values for vehicle Entity class
		vehicleEntity.setColour("black");
		vehicleEntity.setMake("Honda");
		vehicleEntity.setModel("Accord");
		vehicleEntity.setRego("1111");
		vehicleEntity.setYear(2019);
		vehicleEntity.setVehicleID(vehicleId);
		//Adding to the vehicle entity list
		vehicleEntityList.add(vehicleEntity);
		
		
		
	}
	
	@AfterEach
	public void close() throws Exception {
		closeable.close();
	}
	
	
	@Test
	void vehicleControllerSaveTestMethod() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(VehicleRequest.class);		
		Mockito.when(vehicleServiceTest.saveVehicle(vehicleRequest)).thenReturn(vehicleResponse);
		ResponseEntity<VehicleResponse> response = vehicleControllerTest.saveVehicle(vehicleRequest);
		assertEquals(201, response.getStatusCodeValue());
	}
	
	@Test
	void vehicleControllerGetTestMethod() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(VehicleRequest.class);		
		Mockito.when(vehicleServiceTest.getAllVehicles()).thenReturn(vehicleEntityList);
		ResponseEntity<List<VehicleEntity>> response = vehicleControllerTest.getAllVehicles();
		assertEquals(200, response.getStatusCodeValue());	
	}
	
	@Test
	void vehicleControllerGetZeroTestMethod() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(VehicleRequest.class);		
		Mockito.when(vehicleServiceTest.getAllVehicles()).thenReturn(vehicleEntityNoItemsList);
		ResponseEntity<List<VehicleEntity>> response = vehicleControllerTest.getAllVehicles();
		assertEquals(204, response.getStatusCodeValue());	
	}
	
	@Test
	void vehicleControllerGetByIdTestMethod() throws VehicleNotFoundException {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(VehicleRequest.class);		
		Mockito.when(vehicleServiceTest.findByVehicleID(vehicleId)).thenReturn(vehicleEntity);
		ResponseEntity<VehicleEntity> response = vehicleControllerTest.getVehicleById(vehicleId);
		assertEquals(200, response.getStatusCodeValue());	
	}
	

	

}
