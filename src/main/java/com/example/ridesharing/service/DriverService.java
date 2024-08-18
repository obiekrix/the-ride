package com.example.ridesharing.service;

import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.model.PublishGeocodeRequest;

public interface DriverService {

    Driver login(String email, String password);
    Driver getDriverDetails(String email);
    Driver addDriver(Driver driver);
    Driver updateGeocode(PublishGeocodeRequest publishGeocodeRequest);
}
