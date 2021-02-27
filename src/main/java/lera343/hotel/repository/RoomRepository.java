package lera343.hotel.repository;
import lera343.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomsByType_Id(Long id);
}
