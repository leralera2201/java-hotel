package lera343.hotel.controller;

import io.swagger.annotations.ApiParam;
import lera343.hotel.dto.BookingResponse;
import lera343.hotel.entity.Booking;
import lera343.hotel.service.booking.impls.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookingResponse> create(@RequestBody Booking request) {
        return ResponseEntity.ok(bookingService.create(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public ResponseEntity<List<BookingResponse>> getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        return ResponseEntity.ok(bookingService.getAll());
    }

    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public ResponseEntity<List<BookingResponse>> getBookingsByClient(@ApiParam(
            name="clientId",
            example="1"
    ) @PathVariable Long clientId){
        return ResponseEntity.ok(bookingService.getBookingsByClientId(clientId));
    }

    @GetMapping("/room/{roomId}")
    @PreAuthorize("hasAnyRole('ADMIN, USER')")
    public ResponseEntity<List<BookingResponse>> getBookingsByRoom(@ApiParam(
            name="roomId",
            example="1"
    ) @PathVariable Long roomId){
        return ResponseEntity.ok(bookingService.getBookingsByRoomId(roomId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<BookingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> update(@PathVariable Long id, @RequestBody Booking booking){
        return ResponseEntity.ok(bookingService.update(id, booking));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> exceptionHandling() {
        return ResponseEntity.badRequest().build();
    }

}
