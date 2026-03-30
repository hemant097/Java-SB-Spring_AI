package com.example.project.springai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Builder
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private String userId;
    private String destination;
    private Instant departureTime;
    @Enumerated(EnumType.STRING)
    BookingStatus status;

    @CreationTimestamp
    private Instant bookedAt;

}
