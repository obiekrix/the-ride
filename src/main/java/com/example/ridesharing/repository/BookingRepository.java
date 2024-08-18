package com.example.ridesharing.repository;

import com.example.ridesharing.constant.Status;
import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.entity.Booking;
import com.example.ridesharing.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByDriverAndId(Driver driver, Long id);
    List<Booking> findByDriverAndStatus(Driver driver, Status status);
    Booking findByDriverAndRiderAndStatus(Driver driver, Rider rider, Status status);
    Optional<Booking> findByRiderAndId(Rider rider, Long id);
    Optional<Booking> findByRiderAndStatus(Rider rider, Status status);
}
