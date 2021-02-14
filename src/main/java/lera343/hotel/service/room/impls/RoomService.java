package lera343.hotel.service.room.impls;

import lera343.hotel.entity.Room;
import lera343.hotel.repository.RoomRepository;
import lera343.hotel.service.room.interfaces.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RoomService implements IRoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room getById(Long id) {
        Optional<Room> result = roomRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Long id, Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
