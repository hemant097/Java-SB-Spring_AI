package com.example.project.springai.service;

import com.example.project.springai.entity.BookingStatus;
import com.example.project.springai.entity.FlightBooking;
import com.example.project.springai.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FlightBookingService {

    private final BookingRepository bookingRepo;

    public FlightBooking createBooking(String userId, String destination, Instant departureTime){
        boolean exists = doesBookingExist(userId, destination, departureTime);
        if(exists)
            throw new IllegalArgumentException("You already have a booking to "+destination+" on this date");

        FlightBooking booking = FlightBooking.builder()
                .userId(userId)
                .departureTime(departureTime)
                .destination(destination)
                .status(BookingStatus.CONFIRMED)
                .build();

        log.info("Flight is booked for {}, {}, {}",userId, destination, departureTime);
        return bookingRepo.save(booking);
    }

    public List<FlightBooking> getUserBookings(String userId){
        log.info("Querying for bookings of {}", userId);
        List<FlightBooking> bookings =  bookingRepo.findByUserIdOrderByDepartureTimeAsc(userId);
        log.info("{} booking(s) found", bookings.size());
        return bookings;
    }

    public FlightBooking updateBookingStatus(Long bookingId, String userId, BookingStatus newStatus){
        FlightBooking booking = returnFlightIfExists(bookingId);

        if(!booking.getUserId().equals(userId))
            throw new IllegalArgumentException("You can only modify your own bookings");

        booking.setStatus(newStatus);

        log.info("Bookings status updated for {}, {}, new status is {}",booking,userId,newStatus);
        return bookingRepo.save(booking);
    }


    boolean doesBookingExist(String userId, String destination, Instant departureTime){
       return bookingRepo.existsByUserIdAndDestinationAndDepartureTime(userId, destination, departureTime);
    }

    FlightBooking returnFlightIfExists(Long bookingId){
        return bookingRepo.findById(bookingId)
                .orElseThrow( ()-> new IllegalArgumentException("booking not found"));
    }
}
