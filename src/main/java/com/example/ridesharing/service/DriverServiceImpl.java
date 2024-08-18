package com.example.ridesharing.service;

import com.example.ridesharing.constant.ErrorCode;
import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.model.PublishGeocodeRequest;
import com.example.ridesharing.repository.DriverRepository;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final StringEncryptor stringEncryptor;

    public DriverServiceImpl(DriverRepository driverRepository, @Qualifier("jasyptStringEncryptor") StringEncryptor stringEncryptor) {
        this.driverRepository = driverRepository;
        this.stringEncryptor = stringEncryptor;
    }

    @Override
    public Driver login(String email, String password) {
        Optional<Driver> optionalDriver = driverRepository.findByEmail(email);

        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            String decryptedPassword = stringEncryptor.decrypt(driver.getPassword());
            if (password.equals(decryptedPassword)) return driver;
        }
        throw new EntityNotFoundException(ErrorCode.INV_USER.name());
    }

    @Override
    public Driver getDriverDetails(String email) {
        return driverRepository.findByEmail(email).get();
    }

    @Override
    public Driver addDriver(Driver driver) {
        Optional<Driver> optionalDriver = driverRepository.findByEmail(driver.getEmail());

        if (optionalDriver.isPresent()) {
            throw new EntityNotFoundException(ErrorCode.ALREADY_EXISTS.name());
        }

        driver.setPassword(stringEncryptor.encrypt(driver.getPassword()));

        return driverRepository.save(driver);
    }

    @Override
    public Driver updateGeocode(PublishGeocodeRequest publishGeocodeRequest) {
        Optional<Driver> optionalDriver = driverRepository.findByEmail(publishGeocodeRequest.getEmail());

        if (optionalDriver.isEmpty()) {
            throw new EntityNotFoundException(ErrorCode.USER_NOT_FOUND.name());
        }

        Driver driver = optionalDriver.get();
        driver.setLongitude(publishGeocodeRequest.getLongitude());
        driver.setLatitude(publishGeocodeRequest.getLatitude());

        return driverRepository.save(driver);
    }
}
