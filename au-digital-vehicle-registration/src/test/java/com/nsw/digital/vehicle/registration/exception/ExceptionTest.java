package com.nsw.digital.vehicle.registration.exception;

import com.nsw.digital.vehicle.registration.advice.ApplicationExceptionHandler;
import com.nsw.digital.vehicle.registration.model.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class ExceptionTest {

    @Mock
    private ApplicationExceptionHandler applicationExceptionHandler;

    @Test
    public void testHandleBusinessException(){
        Map errorMap= new HashMap<>();
        errorMap.put("Business Exception","ID not found");
        Mockito.when(applicationExceptionHandler.handleBusinessException(ArgumentMatchers.any(VehicleNotFoundException.class))).thenReturn(new ErrorResponse(errorMap));
        ErrorResponse errorResponse= applicationExceptionHandler.handleBusinessException(new VehicleNotFoundException("Vehicle not found"));
       System.out.println(errorResponse);
        Assertions.assertEquals(1,errorResponse.getErrorInfo().size());
    }

    @Test
    public void testHandleInvalidArgument(){
        MethodArgumentNotValidException methodArgumentNotValidException = Mockito.mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        FieldError fieldError = mock(FieldError.class);
        Mockito.when(fieldError.getField()).thenReturn("rego");
        Mockito.when(fieldError.getDefaultMessage()).thenReturn("Please enter valid vehicle rego information");
        Mockito.when(fieldError.getCodes()).thenReturn(new String[] {"message"});
        Mockito.when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError));
        Map errorMap = new HashMap();
        errorMap.put(fieldError.getField(),"Please enter valid vehicle rego information");
        Mockito.when(applicationExceptionHandler.handleInvalidArguement(ArgumentMatchers.any(MethodArgumentNotValidException.class))).thenReturn(new ErrorResponse(errorMap));
        ErrorResponse errorResponse = applicationExceptionHandler.handleInvalidArguement(methodArgumentNotValidException);
        Assertions.assertEquals(1,errorResponse.getErrorInfo().size());
    }



}
