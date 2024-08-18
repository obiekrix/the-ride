package com.example.ridesharing.controller;

import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.entity.Rider;
import com.example.ridesharing.mapper.DriverMapper;
import com.example.ridesharing.mapper.RiderMapper;
import com.example.ridesharing.model.DriverRequest;
import com.example.ridesharing.model.DriverResponse;
import com.example.ridesharing.model.RiderRequest;
import com.example.ridesharing.model.RiderResponse;
import com.example.ridesharing.service.DriverService;
import com.example.ridesharing.service.RiderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(value = {"http://numxlator.com", "http://34.171.142.69", "http://localhost:4200", "http://localhost:8080"}, maxAge = 3600)
public class AdminController {

    private final DriverService driverService;
    private final RiderService riderService;
    private final DriverMapper driverMapper;
    private final RiderMapper riderMapper;

    public AdminController(DriverService driverService, RiderService riderService, DriverMapper driverMapper, RiderMapper riderMapper) {
        this.driverService = driverService;
        this.riderService = riderService;
        this.driverMapper = driverMapper;
        this.riderMapper = riderMapper;
    }

    @PostMapping("/create-driver")
    public ResponseEntity<DriverResponse> create(@Valid @RequestBody DriverRequest driverRequest) {
        Driver driver = driverService.addDriver(driverMapper.requestModelToEntity(driverRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(driverMapper.entityToResponseModel(driver));
    }

    @PostMapping("/create-rider")
    public ResponseEntity<RiderResponse> create(@Valid @RequestBody RiderRequest riderRequest) {
        Rider rider = riderService.addRider(riderMapper.requestModelToEntity(riderRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(riderMapper.entityToResponseModel(rider));
    }

}
