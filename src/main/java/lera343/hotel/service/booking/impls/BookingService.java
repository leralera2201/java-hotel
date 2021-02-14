package lera343.hotel.service.booking.impls;

import lera343.hotel.entity.Booking;
import lera343.hotel.repository.BookingRepository;
import lera343.hotel.service.booking.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookingService implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getById(Long id) {
        Optional<Booking> result = bookingRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Long id, Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }
}
