package lera343.hotel.service.room.interfaces;

import lera343.hotel.entity.Room;

import java.util.List;

public interface IRoomService {
    List<Room> getAll();
    Room getById(Long id);
    Room create(Room room);
    Room update(Long id, Room room);
    void delete(Long id);
}
