package com.example.ridesharing.model;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RiderInitiateRequest {

    @NotNull(message = "Your request must contain an email field for the rider")
    private String riderEmail;

    @NotNull(message = "Your request must contain an email field for the driver")
    private String driverEmail;

    @NotNull(message = "Your request must contain a longitude field for the rider")
    private Integer riderLongitude;

    @NotNull(message = "Your request must contain a latitude field for the rider")
    private Integer riderLatitude;

    @NotNull(message = "Your request must contain a longitude field for the rider's destination")
    private Integer destinationLongitude;

    @NotNull(message = "Your request must contain a latitude field for the rider's destination")
    private Integer destinationLatitude;

}
