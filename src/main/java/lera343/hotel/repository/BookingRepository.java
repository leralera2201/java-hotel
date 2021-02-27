package lera343.hotel.repository;
import lera343.hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsByClient_Id(Long id);
    List<Booking> findBookingsByRoom_Id(Long id);
}
