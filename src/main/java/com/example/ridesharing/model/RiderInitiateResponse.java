package com.example.ridesharing.model;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RiderInitiateResponse {

    private Long bookingId;

    private DriverResponse driverResponse;

}
