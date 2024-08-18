package com.example.ridesharing.service;

import com.example.ridesharing.constant.ErrorCode;
import com.example.ridesharing.constant.Status;
import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.entity.Booking;
import com.example.ridesharing.entity.Rider;
import com.example.ridesharing.model.BookingActionRequest;
import com.example.ridesharing.model.RiderInitiateRequest;
import com.example.ridesharing.repository.DriverRepository;
import com.example.ridesharing.repository.BookingRepository;
import com.example.ridesharing.repository.RiderRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final RiderRepository riderRepository;
    private final DriverRepository driverRepository;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(RiderRepository riderRepository, DriverRepository driverRepository, BookingRepository bookingRepository) {
        this.riderRepository = riderRepository;
        this.driverRepository = driverRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking initiate(RiderInitiateRequest riderInitiateRequest) {
        Rider rider = riderRepository.findByEmail(riderInitiateRequest.getRiderEmail()).get();

        Optional<Booking> initiatedBooking = bookingRepository.findByRiderAndStatus(rider, Status.INITIATED);

        if (initiatedBooking.isPresent()) {
            throw new DataIntegrityViolationException(ErrorCode.ALREADY_EXISTS.name());
        }

        Driver driver = driverRepository.findByEmail(riderInitiateRequest.getDriverEmail()).get();

        Booking booking = new Booking();
        booking.setDriver(driver);
        booking.setRider(rider);
        booking.setStatus(Status.INITIATED);
        booking.setTime(new Date(System.currentTimeMillis()));
        booking.setDestinationLongitude(riderInitiateRequest.getDestinationLongitude());
        booking.setDestinationLatitude(riderInitiateRequest.getDestinationLatitude());
        booking.setPickupLongitude(riderInitiateRequest.getRiderLongitude());
        booking.setPickupLatitude(riderInitiateRequest.getRiderLatitude());

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> checkForRequest(String driverEmail, Status status) {
        Driver driver = driverRepository.findByEmail(driverEmail).get();
        return bookingRepository.findByDriverAndStatus(driver, status);
    }

    @Override
    public Booking checkForRequest(String driverEmail, String riderEmail, Status status) {
        Rider rider = riderRepository.findByEmail(riderEmail).get();
        Driver driver = driverRepository.findByEmail(driverEmail).get();
        return bookingRepository.findByDriverAndRiderAndStatus(driver, rider, status);
    }

    @Override
    public Booking accept(BookingActionRequest bookingActionRequest) {
        return updateStatus(bookingActionRequest, Status.ACCEPTED);
    }

    @Override
    public List<Booking> decline(BookingActionRequest bookingActionRequest) {
        updateStatus(bookingActionRequest, Status.DECLINED);

        return checkForRequest(bookingActionRequest.getEmail(), Status.INITIATED);
    }

    @Override
    public void declineAll(String driverEmail) {
        Driver driver = driverRepository.findByEmail(driverEmail).get();
        List<Booking> bookings = bookingRepository.findByDriverAndStatus(driver, Status.INITIATED);

        for (Booking booking : bookings) {
            booking.setStatus(Status.DECLINED);
            bookingRepository.save(booking);
        }
    }

    @Override
    public Booking cancel(BookingActionRequest bookingActionRequest) {
        Booking booking = getRiderBooking(bookingActionRequest);
        booking.setStatus(Status.CANCELLED);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking end(BookingActionRequest bookingActionRequest) {
        return updateStatus(bookingActionRequest, Status.ENDED);
    }

    @Override
    public Booking checkRequestStatus(BookingActionRequest bookingActionRequest) {
        return getRiderBooking(bookingActionRequest);
    }

    private Booking updateStatus(BookingActionRequest bookingActionRequest, Status status) {
        Driver driver = driverRepository.findByEmail(bookingActionRequest.getEmail()).get();
        Booking booking = bookingRepository.findByDriverAndId(driver, bookingActionRequest.getBookingId()).get();
        booking.setStatus(status);

        return bookingRepository.save(booking);
    }

    private Booking getRiderBooking(BookingActionRequest bookingActionRequest) {
        Rider rider = riderRepository.findByEmail(bookingActionRequest.getEmail()).get();
        Booking booking = bookingRepository.findByRiderAndId(rider, bookingActionRequest.getBookingId()).get();

        return booking;
    }

}
