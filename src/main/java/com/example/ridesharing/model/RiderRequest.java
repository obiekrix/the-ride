package com.example.ridesharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RiderRequest {

    @NotNull(message = "Your request must contain a name field")
    private String name;

    @NotNull(message = "Your request must contain a phone number field")
    private String phoneNo;

    @NotNull(message = "Your request must contain an email field")
    private String email;

    @NotNull(message = "Your request must contain a password field")
    private String password;

    @NotNull(message = "Your request must contain a longitude field")
    private String longitude;

    @NotNull(message = "Your request must contain a latitude field")
    private String latitude;
}
