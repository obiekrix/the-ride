package com.example.ridesharing.service;

import com.example.ridesharing.constant.Status;
import com.example.ridesharing.entity.Booking;
import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.model.BookingActionRequest;
import com.example.ridesharing.model.RiderInitiateRequest;

import java.util.List;

public interface BookingService {

    Booking initiate(RiderInitiateRequest riderInitiateRequest);

    List<Booking> checkForRequest(String driverEmail, Status status);

    Booking checkForRequest(String driverEmail, String riderEmail, Status status);

    Booking accept(BookingActionRequest bookingActionRequest);

    List<Booking> decline(BookingActionRequest bookingActionRequest);

    void declineAll(String driverEmail);

    Booking cancel(BookingActionRequest driverActionRequest);

    Booking end(BookingActionRequest bookingActionRequest);

    Booking checkRequestStatus(BookingActionRequest bookingActionRequest);
}
