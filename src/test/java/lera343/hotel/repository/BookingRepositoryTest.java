package lera343.hotel.repository;

import lera343.hotel.entity.Booking;
import lera343.hotel.entity.Type;
import lera343.hotel.stubs.BookingStub;
import lera343.hotel.stubs.ClientStub;
import lera343.hotel.stubs.RoomStub;
import lera343.hotel.stubs.TypeStub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TestEntityManager entityManager;
    private Booking expectedBooking;

    @BeforeEach
    void setUp() {
        expectedBooking = BookingStub.getRandomBooking();
        entityManager.persist(expectedBooking);
        entityManager.flush();
    }

    @Test
    void testFindBookingsByClient() {
        var actualBookings = bookingRepository.findBookingsByClient_Id(ClientStub.ID);
        Assertions.assertThat(actualBookings).hasSize(1);
    }

    @Test
    void testFindBookingsByRoom() {
        var actualBookings = bookingRepository.findBookingsByRoom_Id(RoomStub.ID);
        Assertions.assertThat(actualBookings).hasSize(1);
    }

}