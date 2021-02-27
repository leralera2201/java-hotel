package lera343.hotel.service.booking.interfaces;

import lera343.hotel.dto.BookingResponse;
import lera343.hotel.entity.Booking;

import java.util.List;

public interface IBookingService {
    List<BookingResponse> getAll();
    List<BookingResponse> getBookingsByClientId(Long id);
    List<BookingResponse> getBookingsByRoomId(Long id);
    BookingResponse getById(Long id);
    BookingResponse create(Booking booking);
    BookingResponse update(Long id, Booking booking);
    void delete(Long id);
}
