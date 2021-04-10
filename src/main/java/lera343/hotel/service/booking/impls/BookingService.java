package lera343.hotel.service.booking.impls;

import lera343.hotel.dto.BookingResponse;
import lera343.hotel.dto.ClientResponse;
import lera343.hotel.entity.Booking;
import lera343.hotel.repository.BookingRepository;
import lera343.hotel.service.booking.interfaces.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;
    @Override
    public List<BookingResponse> getAll() {
        var bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingResponse::mapToBookingResponse).collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getBookingsByClientId(Long id) {
        var bookings = bookingRepository.findBookingsByClient_Id(id);
        return bookings.stream().map(BookingResponse::mapToBookingResponse).collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getBookingsByRoomId(Long id) {
        var bookings = bookingRepository.findBookingsByRoom_Id(id);
        return bookings.stream().map(BookingResponse::mapToBookingResponse).collect(Collectors.toList());
    }

    @Override
    public BookingResponse getById(Long id) {
        var booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return BookingResponse.mapToBookingResponse(booking);
    }

    @Override
    public BookingResponse create(Booking booking) {
        return BookingResponse.mapToBookingResponse(bookingRepository.save(booking));
    }

    @Override
    public BookingResponse update(Long id, Booking booking) {
        var bookingToUpdate = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return BookingResponse.mapToBookingResponse(bookingRepository.save(bookingToUpdate));
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}
