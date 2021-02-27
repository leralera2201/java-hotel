package lera343.hotel.dto;

import lera343.hotel.entity.Room;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomResponse {
    private Long id;
    private String name;
    private String description;
    private Integer placesCount;
    private BigDecimal price;
    private TypeResponse type;

    public static RoomResponse mapToRoomResponse(Room room) {
        RoomResponse roomResponse = new RoomResponse();

        roomResponse.setId(room.getId());
        roomResponse.setDescription(room.getDescription());
        roomResponse.setName(room.getName());
        roomResponse.setPlacesCount(room.getPlacesCount());
        roomResponse.setPrice(room.getPrice());
        roomResponse.setType(TypeResponse.mapToTypeResponse(room.getType()));

        return roomResponse;
    }

}
