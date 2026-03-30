package com.example.project.springai.dto;

import com.example.project.springai.entity.BookingStatus;
import com.example.project.springai.entity.FlightBooking;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class BookingResponse {

    private Long id;
    private String destination;
    private Instant departureTime;
    private BookingStatus status;

    public static BookingResponse of(FlightBooking booking){
        return new BookingResponse(booking.getId(), booking.getDestination(), booking.getDepartureTime(), booking.getStatus());
    }

}
