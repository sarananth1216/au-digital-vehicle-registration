package com.nsw.digital.vehicle.registration.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {


    @NotNull(message = "Please enter valid vehicle rego information")
    @Size(max = 7)
    public String rego;
    @NotNull(message = "Please enter valid vehicle make information")
    @Size(max = 20)
    public String make;
    @NotNull(message = "Please enter vehicle model information")
    @Size(max = 20)
    public String model;
    @NotNull(message = "Please enter vehicle year information")
    @Range(min = 1800, max = 2023)
    public int year;
    @NotNull(message = "Please enter vehicle colour information")
    @Size(max = 20)
    public String colour;

    

}
