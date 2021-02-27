package lera343.hotel.controller;

import io.swagger.annotations.ApiParam;
import lera343.hotel.dto.BookingResponse;
import lera343.hotel.entity.Booking;
import lera343.hotel.service.booking.impls.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    @GetMapping
    public List<BookingResponse> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "10") Integer size){
        return bookingService.getAll();
    }
    @GetMapping("/{id}")
    public BookingResponse getById(@PathVariable Long id){
        return bookingService.getById(id);
    }

    @PostMapping
    public BookingResponse create(@RequestBody Booking booking){
        return bookingService.create(booking);
    }

    @PutMapping("/{id}")
    public BookingResponse update(@ApiParam(
            name="bookingId",
            example="1"
    ) @PathVariable Long id, @RequestBody Booking booking){
        return bookingService.update(id, booking);
    }

    @DeleteMapping("/{id}")
    public void delete(@ApiParam(
            name="bookingId",
            example="1"
    ) @PathVariable Long id){
        bookingService.delete(id);
    }

    @GetMapping("/{clientId}/bookings")
    public List<BookingResponse> getBookingsByClient(@ApiParam(
            name="clientId",
            example="1"
    ) @PathVariable Long clientId){
        return bookingService.getBookingsByClientId(clientId);
    }

    @GetMapping("/{roomId}/bookings")
    public List<BookingResponse> getBookingsByRoom(@ApiParam(
            name="roomId",
            example="1"
    ) @PathVariable Long roomId){
        return bookingService.getBookingsByRoomId(roomId);
    }
}
