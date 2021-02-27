package lera343.hotel.service.room.interfaces;

import lera343.hotel.dto.RoomRequest;
import lera343.hotel.dto.RoomResponse;
import lera343.hotel.entity.Room;

import java.util.List;

public interface IRoomService {
    List<RoomResponse> getAll();
    List<RoomResponse> getRoomsByTypeId(Long id);
    RoomResponse getById(Long id);
    RoomResponse create(RoomRequest room);
    RoomResponse update(Long id, Room room);
    void delete(Long id);
}
