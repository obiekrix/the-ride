package com.example.ridesharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PublishGeocodeRequest {

    @NotNull(message = "Your request must contain an email field")
    private String email;

    @NotNull(message = "Your request must contain a longitude field")
    private Integer longitude;

    @NotNull(message = "Your request must contain a latitude field")
    private Integer latitude;

}
