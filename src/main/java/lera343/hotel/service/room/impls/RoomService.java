package lera343.hotel.service.room.impls;

import lera343.hotel.dto.RoomRequest;
import lera343.hotel.dto.RoomResponse;
import lera343.hotel.dto.TypeResponse;
import lera343.hotel.entity.Room;
import lera343.hotel.repository.RoomRepository;
import lera343.hotel.service.room.interfaces.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    @Override
    public List<RoomResponse> getAll() {
        var rooms = roomRepository.findAll();
        return rooms.stream().map(RoomResponse::mapToRoomResponse).collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> getRoomsByTypeId(Long id) {
        var rooms = roomRepository.findRoomsByType_Id(id);
        return rooms.stream().map(RoomResponse::mapToRoomResponse).collect(Collectors.toList());
    }

    @Override
    public RoomResponse getById(Long id) {
        Optional<Room> result = roomRepository.findById(id);
        if (result.isPresent()) {
            return RoomResponse.mapToRoomResponse(result.get());
        } else {
            return null;
        }
    }

    @Override
    public RoomResponse create(RoomRequest room) {
        var newRoom = Room.builder()
                .id(new Random().nextLong())
                .name(room.getName())
                .description(room.getDescription())
                .placesCount(room.getPlacesCount())
                .price(room.getPrice())
                .type(room.getType())
                .bookings(new HashSet<>())
                .build();
        return RoomResponse.mapToRoomResponse(roomRepository.save(newRoom));
    }

    @Override
    public RoomResponse update(Long id, Room room) {
        return RoomResponse.mapToRoomResponse(roomRepository.save(room));
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
