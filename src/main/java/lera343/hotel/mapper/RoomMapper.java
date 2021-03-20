package lera343.hotel.mapper;

import lera343.hotel.dto.RoomRequest;
import lera343.hotel.entity.Room;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;

@Component
public class RoomMapper {
    public Room fromRequest(RoomRequest request) {
        return Room.builder()
                .id(new Random().nextLong())
                .name(request.getName())
                .description(request.getDescription())
                .placesCount(request.getPlacesCount())
                .price(request.getPrice())
                .type(request.getType())
                .bookings(new HashSet<>())
                .build();
    }
}
