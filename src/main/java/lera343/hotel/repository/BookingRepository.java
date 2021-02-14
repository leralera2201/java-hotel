package lera343.hotel.repository;
import lera343.hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends JpaRepository<Booking, Long> {
}
