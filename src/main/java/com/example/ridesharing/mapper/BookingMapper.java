package com.example.ridesharing.mapper;

import com.example.ridesharing.entity.Booking;
import com.example.ridesharing.model.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "riderResponse", source = "booking.rider")
    @Mapping(target = "driverResponse", source = "booking.driver")
    BookingResponse entityToResponseModel(Booking booking);
    List<BookingResponse> entitiesToResponseModels(List<Booking> bookings);
}
