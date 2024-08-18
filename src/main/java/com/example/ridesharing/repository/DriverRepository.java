package com.example.ridesharing.repository;

import com.example.ridesharing.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByEmail(String email);

    @Query("SELECT d FROM Driver d WHERE ABS(d.longitude - :longitude) < 500 AND ABS(d.latitude - :latitude) < 500")
    Optional<List<Driver>> findDriversWithinRidersGeocode(Integer longitude, Integer latitude);
}
