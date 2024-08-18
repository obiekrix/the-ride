package com.example.ridesharing.mapper;

import com.example.ridesharing.entity.Driver;
import com.example.ridesharing.entity.Rider;
import com.example.ridesharing.model.DriverResponse;
import com.example.ridesharing.model.RiderRequest;
import com.example.ridesharing.model.RiderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RiderMapper {

    Rider requestModelToEntity(RiderRequest riderRequest);
    RiderRequest entityToRequestModel(Rider rider);

    RiderResponse entityToResponseModel(Rider driver);
}
