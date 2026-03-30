package com.example.project.springai.repository;

import com.example.project.springai.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<FlightBooking, Long> {

    List<FlightBooking> findByUserIdOrderByDepartureTimeAsc(String userId);

    boolean existsByUserIdAndDestinationAndDepartureTime(String userId, String destination, Instant departureTime);
}
