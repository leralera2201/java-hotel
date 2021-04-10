package lera343.hotel.service.room.impls;

import lera343.hotel.dto.RoomRequest;
import lera343.hotel.dto.RoomResponse;
import lera343.hotel.dto.TypeResponse;
import lera343.hotel.entity.Room;
import lera343.hotel.mapper.RoomMapper;
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
    private final RoomMapper roomMapper;
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
        var room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return RoomResponse.mapToRoomResponse(room);
    }

    @Override
    public RoomResponse create(RoomRequest room) {
        var newRoom = roomMapper.fromRequest(room);
        return RoomResponse.mapToRoomResponse(roomRepository.save(newRoom));
    }

    @Override
    public RoomResponse update(Long id, RoomRequest request) {
        var room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        room.setDescription(request.getDescription());
        room.setName(request.getName());
        room.setPrice(request.getPrice());
        room.setPlacesCount(request.getPlacesCount());
        room.setType(request.getType());
        return RoomResponse.mapToRoomResponse(roomRepository.save(room));
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
