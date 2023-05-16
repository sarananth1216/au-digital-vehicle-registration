package com.nsw.digital.vehicle.registration.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleConfiguration {
    @Value("${server.port}")
    String serverPort;
}
