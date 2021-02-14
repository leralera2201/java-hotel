package lera343.hotel.repository;
import lera343.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Long> {
}
