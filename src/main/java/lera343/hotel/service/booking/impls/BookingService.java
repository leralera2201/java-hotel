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
        Optional<Booking> result = bookingRepository.findById(id);
        if (result.isPresent()) {
            return BookingResponse.mapToBookingResponse(result.get());
        } else {
            return BookingResponse.mapToBookingResponse(result.orElseThrow());
        }
    }

    @Override
    public BookingResponse create(Booking booking) {
        return BookingResponse.mapToBookingResponse(bookingRepository.save(booking));
    }

    @Override
    public BookingResponse update(Long id, Booking booking) {
        return BookingResponse.mapToBookingResponse(bookingRepository.save(booking));
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}
