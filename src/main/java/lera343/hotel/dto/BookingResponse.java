package lera343.hotel.dto;

import lera343.hotel.entity.Booking;
import lera343.hotel.entity.Client;
import lombok.Data;

import java.util.Date;

@Data
public class BookingResponse {
    private Long id;
    private String description;
    private Date arrivalDate;
    private Date departureDate;
    private Long room_id;
    private Long client_id;

    public static BookingResponse mapToBookingResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse();

        bookingResponse.setId(booking.getId());
        bookingResponse.setDescription(booking.getDescription());
        bookingResponse.setArrivalDate(booking.getArrivalDate());
        bookingResponse.setDepartureDate(booking.getDepartureDate());
        bookingResponse.setClient_id(booking.getClient().getId());
        bookingResponse.setRoom_id(booking.getRoom().getId());

        return bookingResponse;
    }

}
