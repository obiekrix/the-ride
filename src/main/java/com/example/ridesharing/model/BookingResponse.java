package com.example.ridesharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookingResponse {

    private Long id;
    private Integer pickupLongitude;

    private Integer pickupLatitude;

    private Integer destinationLongitude;

    private Integer destinationLatitude;

    private String status;

    private RiderResponse riderResponse;

    private DriverResponse driverResponse;
}
