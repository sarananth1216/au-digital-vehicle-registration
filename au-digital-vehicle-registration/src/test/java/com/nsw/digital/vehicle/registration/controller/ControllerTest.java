package com.nsw.digital.vehicle.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsw.digital.vehicle.registration.exception.VehicleNotFoundException;
import com.nsw.digital.vehicle.registration.model.VehicleRequest;
import com.nsw.digital.vehicle.registration.model.VehicleResponse;
import com.nsw.digital.vehicle.registration.persist.dto.VehicleEntity;
import com.nsw.digital.vehicle.registration.persist.repo.VehicleRepo;
import com.nsw.digital.vehicle.registration.service.VehicleService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VehicleController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VehicleController vehicleController;
    @MockBean
    VehicleService vehicleService;

    @Mock
    private VehicleRepo vehicleRepo;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }

    @Test
    public void testSaveVehicle() throws Exception
    {
        VehicleEntity vehicle = new VehicleEntity(200L,"CX78GD","Nissan","xTrail",1987,"red");
        Mockito.when(vehicleRepo.save(new VehicleEntity(200L,"CX78GD","Nissan","xTrail",1987,"red"))).thenReturn(vehicle);
        Mockito.when(vehicleService.saveVehicle(new VehicleRequest("CX78GD","Nissan","xTrail",1987,"red"))).thenReturn(new VehicleResponse(200L,"Success"));
        mockMvc.perform( post("/vehicles/addVehicle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new VehicleRequest("CX78GD","Nissan","xTrail",1987,"red")))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void testGetAllVehicles() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/vehicles/getAllVehicles")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetVehicleById() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/vehicles/{id}", 200)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
