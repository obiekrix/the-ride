package com.example.ridesharing.mapper;

import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.model.DriverRequest;
import com.example.ridesharing.model.DriverResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver requestModelToEntity(DriverRequest driverRequest);
    DriverRequest entityToRequestModel(Driver driver);
    DriverResponse entityToResponseModel(Driver driver);
    List<DriverResponse> entitiesToResponseModels(List<Driver> drivers);
}
