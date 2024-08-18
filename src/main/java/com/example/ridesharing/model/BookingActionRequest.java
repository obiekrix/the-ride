package com.example.ridesharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookingActionRequest {

    @NotNull(message = "Your request must contain the id of the booking")
    private Long bookingId;
    @NotNull(message = "Your request must contain an email field")
    private String email;

}
