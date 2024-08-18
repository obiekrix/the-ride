package com.example.ridesharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DriverRequest {

    @NotNull(message = "Your request must contain a name field")
    private String name;

    @NotNull(message = "Your request must contain a phone number field")
    private String phoneNo;

    @NotNull(message = "Your request must contain an email field")
    private String email;

    @NotNull(message = "Your request must contain a password field")
    private String password;

    @NotNull(message = "Your request must contain a driver license field")
    private String driverLicense;

    @NotNull(message = "Your request must contain a car registration number field")
    private String carRegistrationNo;

    @NotNull(message = "Your request must contain a car make field e.g. Toyota-Camry")
    private String carMake;

    @NotNull(message = "Your request must contain a car color field")
    private String carColor;

    @NotNull(message = "Your request must contain a longitude field")
    private String longitude;

    @NotNull(message = "Your request must contain a latitude field")
    private String latitude;

}
