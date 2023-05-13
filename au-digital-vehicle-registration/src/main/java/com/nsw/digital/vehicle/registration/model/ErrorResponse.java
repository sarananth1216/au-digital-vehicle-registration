package com.nsw.digital.vehicle.registration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data

public class ErrorResponse {

    Map<String, String> errorInfo = new HashMap<>();

	public ErrorResponse(Map<String, String> errorInfo) {
		super();
		this.errorInfo = errorInfo;
	}
    
    
}
