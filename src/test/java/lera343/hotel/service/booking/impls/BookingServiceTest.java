package lera343.hotel.service.booking.impls;

import lera343.hotel.entity.Booking;
import lera343.hotel.repository.BookingRepository;
import lera343.hotel.stubs.BookingStub;
import lera343.hotel.stubs.ClientStub;
import lera343.hotel.stubs.RoomStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    private BookingService service;
    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void setup() {
        service = new BookingService(bookingRepository);
    }

    @Test
    void getSuccessful() {
        List<Booking> list = new ArrayList<Booking>();
        var booking = BookingStub.getRandomBooking();
        list.add(booking);
        list.add(booking);
        list.add(booking);

        when(bookingRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }


    @Test
    void getSuccessfulByClient() {
        List<Booking> list = new ArrayList<Booking>();
        var booking = BookingStub.getRandomBooking();
        list.add(booking);
        list.add(booking);
        list.add(booking);

        when(bookingRepository.findBookingsByClient_Id(ClientStub.ID)).thenReturn(list);
        var getAll = service.getBookingsByClientId(ClientStub.ID);
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulByRoom() {
        List<Booking> list = new ArrayList<Booking>();
        var booking = BookingStub.getRandomBooking();
        list.add(booking);
        list.add(booking);
        list.add(booking);

        when(bookingRepository.findBookingsByRoom_Id(RoomStub.ID)).thenReturn(list);
        var getAll = service.getBookingsByRoomId(RoomStub.ID);
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var booking = BookingStub.getRandomBooking();
        when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.of(booking));
        var byId = service.getById(BookingStub.ID);

        assertAll(() -> assertEquals(byId.getId(), booking.getId()),
                () -> assertEquals(byId.getArrivalDate(), booking.getArrivalDate()),
                () -> assertEquals(byId.getDescription(), booking.getDescription()),
                () -> assertEquals(byId.getDepartureDate(), booking.getDepartureDate()),
                () -> assertEquals(byId.getClient_id(), booking.getClient().getId()),
                () -> assertEquals(byId.getRoom_id(), booking.getRoom().getId()));
    }


    @Test
    void getFailedById() {
        when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(NoSuchElementException.class, () -> service.getById(BookingStub.ID));
        assertEquals(e.getMessage(), "No value present");
    }

    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Booking.class);
        var personnel = BookingStub.getRandomBooking();
        when(bookingRepository.save(Mockito.any())).thenReturn(BookingStub.getRandomBooking());
        var result = service.create(BookingStub.getRandomBooking());
        Mockito.verify(bookingRepository, atLeast(1)).save(captor.capture());
        assertEquals(personnel, captor.getValue());
        assertEquals(personnel.getId(), result.getId());
        assertEquals(personnel.getDescription(), result.getDescription());
        assertEquals(personnel.getArrivalDate(), result.getArrivalDate());
        assertEquals(personnel.getDepartureDate(), result.getDepartureDate());
    }


    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Booking.class);
        var personnel = BookingStub.getRandomBooking();
        when(bookingRepository.save(Mockito.any())).thenReturn(BookingStub.getRandomBooking());
        var result = service.update(BookingStub.ID, BookingStub.getRandomBooking());
        Mockito.verify(bookingRepository, atLeast(1)).save(captor.capture());
        assertEquals(personnel, captor.getValue());
        assertEquals(personnel.getId(), result.getId());
        assertEquals(personnel.getDescription(), result.getDescription());
        assertEquals(personnel.getArrivalDate(), result.getArrivalDate());
        assertEquals(personnel.getDepartureDate(), result.getDepartureDate());
    }

    @Test
    void deleteSuccessful() {
        service.delete(BookingStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(bookingRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(BookingStub.ID, captor.getValue());
    }
}