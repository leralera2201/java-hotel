package lera343.hotel.controller;

import lera343.hotel.entity.Booking;
import lera343.hotel.service.booking.impls.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    BookingService bookingService;
    @GetMapping
    public List<Booking> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "10") Integer size){
        return bookingService.getAll();
    }
    @GetMapping("/{id}")
    public Booking getById(@PathVariable Long id){
        return bookingService.getById(id);
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking){
        return bookingService.create(booking);
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable Long id, @RequestBody Booking booking){
        return bookingService.update(id, booking);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        bookingService.delete(id);
    }
}
