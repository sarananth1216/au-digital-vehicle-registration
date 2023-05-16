package com.nsw.digital.vehicle.registration.advice;


import com.nsw.digital.vehicle.registration.exception.VehicleAlreadyExistsException;
import com.nsw.digital.vehicle.registration.exception.VehicleNotFoundException;
import com.nsw.digital.vehicle.registration.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInvalidArguement(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        ErrorResponse errorResponse = new ErrorResponse(errorMap);
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VehicleNotFoundException.class)
    public ErrorResponse handleBusinessException(VehicleNotFoundException ex){
        Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("Business Exception",ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorMap);
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public ErrorResponse VehicleAlreadyExistsException(VehicleAlreadyExistsException ex){
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("Already Exists Exception",ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorMap);
        return errorResponse;
    }
}
