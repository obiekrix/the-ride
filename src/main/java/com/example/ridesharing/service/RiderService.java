package com.example.ridesharing.service;

import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.entity.Rider;
import com.example.ridesharing.model.PublishGeocodeRequest;
import com.example.ridesharing.model.RiderInitiateRequest;

import java.util.List;

public interface RiderService {

    Rider login(String email, String password);

    Rider addRider(Rider rider);

    Rider updateGeocode(PublishGeocodeRequest publishGeocodeRequest);

    List<Driver> findNearbyRides(int longitude, int latitude);

    void requestRide(RiderInitiateRequest riderInitiateRequest);

}
