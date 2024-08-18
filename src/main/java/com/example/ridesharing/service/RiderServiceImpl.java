package com.example.ridesharing.service;

import com.example.ridesharing.constant.ErrorCode;
import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.entity.Rider;
import com.example.ridesharing.model.PublishGeocodeRequest;
import com.example.ridesharing.model.RiderInitiateRequest;
import com.example.ridesharing.repository.DriverRepository;
import com.example.ridesharing.repository.RiderRepository;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RiderServiceImpl implements RiderService {

    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final StringEncryptor stringEncryptor;

    public RiderServiceImpl(DriverRepository driverRepository, RiderRepository riderRepository, @Qualifier("jasyptStringEncryptor") StringEncryptor stringEncryptor) {
        this.driverRepository = driverRepository;
        this.riderRepository = riderRepository;
        this.stringEncryptor = stringEncryptor;
    }

    @Override
    public Rider login(String email, String password) {
        Optional<Rider> optionalRider = riderRepository.findByEmail(email);

        if (optionalRider.isPresent()) {
            Rider rider = optionalRider.get();
            String decryptedPassword = stringEncryptor.decrypt(rider.getPassword());
            if (password.equals(decryptedPassword)) return rider    ;
        }
        throw new EntityNotFoundException(ErrorCode.INV_USER.name());
    }

    @Override
    public Rider addRider(Rider rider) {
        Optional<Rider> optionalRider = riderRepository.findByEmail(rider.getEmail());

        if (optionalRider.isPresent()) {
            throw new EntityNotFoundException(ErrorCode.ALREADY_EXISTS.name());
        }

        rider.setPassword(stringEncryptor.encrypt(rider.getPassword()));

        return riderRepository.save(rider);
    }

    @Override
    public Rider updateGeocode(PublishGeocodeRequest publishGeocodeRequest) {
        Optional<Rider> optionalRider = riderRepository.findByEmail(publishGeocodeRequest.getEmail());

        if (optionalRider.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND.name());
        }

        Rider rider = optionalRider.get();
        rider.setLongitude(publishGeocodeRequest.getLongitude());
        rider.setLatitude(publishGeocodeRequest.getLatitude());

        return riderRepository.save(rider);
    }

    @Override
    public List<Driver> findNearbyRides(int longitude, int latitude) {
        Optional<List<Driver>> optionalDrivers = driverRepository.findDriversWithinRidersGeocode(longitude, latitude);

        if (optionalDrivers.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.NO_RIDE_AVAILABLE.name());
        }

        //sort rides in descending order
        List<Driver> drivers = optionalDrivers.get();
        drivers.sort((d1, d2) -> {
            int diffInLongitudeForDriver1 = Math.abs(longitude - d1.getLongitude());
            int diffInLatitudeForDriver1 = Math.abs(latitude - d1.getLatitude());
            int sumOfDriver1 = diffInLongitudeForDriver1 + diffInLatitudeForDriver1;

            int diffInLongitudeForDriver2 = Math.abs(longitude - d2.getLongitude());
            int diffInLatitudeForDriver2 = Math.abs(latitude - d2.getLatitude());
            int sumOfDriver2 = diffInLongitudeForDriver2 + diffInLatitudeForDriver2;

            return sumOfDriver1 - sumOfDriver2;
        });

        //return top 5 drivers
        return drivers.size() > 5 ? drivers.subList(0, 5) : drivers;
    }

    @Override
    public void requestRide(RiderInitiateRequest riderInitiateRequest) {

    }
}
