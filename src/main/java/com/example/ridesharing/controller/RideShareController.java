package com.example.ridesharing.controller;

import com.example.ridesharing.constant.Status;
import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.entity.Booking;
import com.example.ridesharing.entity.Rider;
import com.example.ridesharing.mapper.BookingMapper;
import com.example.ridesharing.mapper.DriverMapper;
import com.example.ridesharing.mapper.RiderMapper;
import com.example.ridesharing.model.*;
import com.example.ridesharing.service.DriverService;
import com.example.ridesharing.service.BookingService;
import com.example.ridesharing.service.RiderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = {"http://numxlator.com", "http://34.171.142.69", "http://localhost:4200", "http://localhost:8080"}, maxAge = 3600)
public class RideShareController {

    private final BookingService bookingService;
    private final DriverService driverService;
    private final RiderService riderService;
    private final BookingMapper bookingMapper;
    private final DriverMapper driverMapper;
    private final RiderMapper riderMapper;

    public RideShareController(BookingService bookingService, DriverService driverService, RiderService riderService, BookingMapper bookingMapper, DriverMapper driverMapper, RiderMapper riderMapper) {
        this.bookingService = bookingService;
        this.driverService = driverService;
        this.riderService = riderService;
        this.bookingMapper = bookingMapper;
        this.driverMapper = driverMapper;
        this.riderMapper = riderMapper;
    }

    @PostMapping("/driver/login")
    public ResponseEntity<DriverResponse> driverLogin(@Valid @RequestBody LoginRequest loginRequest) {
        Driver driver = driverService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(driverMapper.entityToResponseModel(driver));
    }

    @PostMapping("/rider/login")
    public ResponseEntity<RiderResponse> riderLogin(@Valid @RequestBody LoginRequest loginRequest) {
        Rider rider = riderService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(riderMapper.entityToResponseModel(rider));
    }

    @PostMapping("/driver/publish-geocode")
    public ResponseEntity<DriverResponse> publishDriverGeocode(@Valid @RequestBody PublishGeocodeRequest publishGeocodeRequest) {
        Driver driver = driverService.updateGeocode(publishGeocodeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(driverMapper.entityToResponseModel(driver));
    }

    @PostMapping("/rider/publish-geocode")
    public ResponseEntity<RiderResponse> publishRiderGeocode(@Valid @RequestBody PublishGeocodeRequest publishGeocodeRequest) {
        Rider rider = riderService.updateGeocode(publishGeocodeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(riderMapper.entityToResponseModel(rider));
    }

    @GetMapping("/find-nearby-rides/{longitude}/{latitude}")
    public ResponseEntity<List<DriverResponse>> findNearbyRides(@Valid @PathVariable("longitude") String longitude, @Valid @PathVariable("latitude") String latitude) {
        List<Driver> drivers = riderService.findNearbyRides(Integer.parseInt(longitude), Integer.parseInt(latitude));
        return ResponseEntity.status(HttpStatus.OK).body(driverMapper.entitiesToResponseModels(drivers));
    }

    @PostMapping("/request-ride")
    public ResponseEntity<BookingResponse> requestRide(@Valid @RequestBody RiderInitiateRequest riderInitiateRequest) {
        Booking booking = bookingService.initiate(riderInitiateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(bookingMapper.entityToResponseModel(booking));
    }

    @PostMapping("/cancel-request")
    public ResponseEntity<Void> cancelRequest(@Valid @RequestBody BookingActionRequest bookingActionRequest) {
        bookingService.cancel(bookingActionRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/check-for-request/{email}")
    public ResponseEntity<List<BookingResponse>> checkForRequest(@Valid @PathVariable("email") String driverEmail) {
        List<Booking> bookings = bookingService.checkForRequest(driverEmail, Status.INITIATED);
        return ResponseEntity.status(HttpStatus.OK).body(bookingMapper.entitiesToResponseModels(bookings));
    }

    @GetMapping("/check-request-status/{email}/{id}")
    public ResponseEntity<BookingResponse> checkRequestStatus(@Valid @PathVariable("email") String riderEmail, @Valid @PathVariable("id") String bookingId) {
        BookingActionRequest bookingActionRequest = new BookingActionRequest();
        bookingActionRequest.setBookingId(Long.parseLong(bookingId));
        bookingActionRequest.setEmail(riderEmail);
        Booking bookings = bookingService.checkRequestStatus(bookingActionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(bookingMapper.entityToResponseModel(bookings));
    }

    @PostMapping("/accept-request")
    public ResponseEntity<BookingResponse> acceptRequest(@Valid @RequestBody BookingActionRequest bookingActionRequest) {
        Booking booking = bookingService.accept(bookingActionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(bookingMapper.entityToResponseModel(booking));
    }

    @PostMapping("/decline-request")
    public ResponseEntity<List<BookingResponse>> declineRequest(@Valid @RequestBody BookingActionRequest bookingActionRequest) {
        List<Booking> bookings = bookingService.decline(bookingActionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(bookingMapper.entitiesToResponseModels(bookings));
    }

    @GetMapping("/decline-all-requests/{email}")
    public ResponseEntity<Void> declineAllRequests(@Valid @PathVariable("email") String driverEmail) {
        bookingService.declineAll(driverEmail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/end-request")
    public ResponseEntity<Void> endRequest(@Valid @RequestBody BookingActionRequest bookingActionRequest) {
        bookingService.end(bookingActionRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/get-driver-details/{driverEmail}/{riderEmail}")
    public ResponseEntity<BookingResponse> getDriverDetails(@Valid @PathVariable String driverEmail, @Valid @PathVariable String riderEmail) {
        Booking booking = bookingService.checkForRequest(driverEmail, riderEmail, Status.ACCEPTED);
        return ResponseEntity.status(HttpStatus.OK).body(bookingMapper.entityToResponseModel(booking));
    }

}
