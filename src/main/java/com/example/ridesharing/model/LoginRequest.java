package com.example.ridesharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "Your request must contain an email field")
    private String email;

    @NotNull(message = "Your request must contain a password field")
    private String password;
}
