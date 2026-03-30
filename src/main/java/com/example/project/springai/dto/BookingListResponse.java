package com.example.project.springai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookingListResponse {
    private List<BookingResponse> bookings;
    private String message;
    private int size;
}
