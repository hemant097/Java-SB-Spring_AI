package com.example.project.springai.tool;

import com.example.project.springai.dto.BookingResponse;
import com.example.project.springai.dto.BookingListResponse;
import com.example.project.springai.entity.BookingStatus;
import com.example.project.springai.entity.FlightBooking;
import com.example.project.springai.service.FlightBookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FlightBookingTool {
    private final FlightBookingService bookingService;

    @Tool(
            name = "flight_booking_tool",
            description = "Create a flight booking for the user"
    )
    public BookingResponse createBooking(
            @ToolParam(description = "The unique userId (e.g., userId is user123)")
            String userId,
            @ToolParam(description = "The destination for the flight booking (e.g., city like Mumbai, London, etc)")
            String destination,
            @ToolParam(description = "departure date and time in iso 8601 date format (e.g., 2011-10-05T14:48:00.000Z)")
            Instant departureTime){
        FlightBooking booking = bookingService.createBooking(userId, destination, departureTime);

        log.info("Inside FlightBookingTool, created a booking for {} to {}", userId, destination);
        return BookingResponse.of(booking);

    }

    @Tool(
            name = "get_user_booking",
            description = "Retrieve all flight bookings for the given userId, sorted by departure time (most recent first)" +
                    "Always returns a list of bookings. If no bookings exist, the list will be empty."
    )
    public BookingListResponse getUserBookings(
            @ToolParam(description = "The unique userId ")
            String userId){
        List<BookingResponse> bookingDtoList =  bookingService.getUserBookings(userId).stream()
                .map(BookingResponse::of)
                .toList();

        log.info("Inside FlightBookingTool, returned a bookingsDtoList of size:{} ",bookingDtoList.size());

        String message = bookingDtoList.isEmpty()
                ? "You have no upcoming flight bookings."
                : "Here are your upcoming flight bookings";

        int numberOfBookings = bookingDtoList.size();

        return new BookingListResponse(bookingDtoList, message, numberOfBookings);

    }

    @Tool(
            name = "update_user_booking",
            description = "Update the status of an existing flight booking (e.g., cancel it) " +
                    "only the owner of the booking can cancel it. " +
                    "Common use case: set status to CANCELLED"
    )
    public BookingResponse updateBookingStatus(
            @ToolParam(description = "The booking id returned from create or get bookings")
            Long bookingId,
            @ToolParam(description = "The userId who owns the booking")
            String userId,
            @ToolParam(description = "new Status: CONFIRMED, CANCELLED, OR PENDING")
            BookingStatus status){

        FlightBooking booking = bookingService.updateBookingStatus(bookingId,userId, status);

        log.info("Inside FlightBookingTool, updated the booking Id {} of user :{} ",booking, userId);
        return BookingResponse.of(booking);

    }

}
