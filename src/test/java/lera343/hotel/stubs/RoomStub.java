package lera343.hotel.stubs;

import lera343.hotel.dto.RoomRequest;
import lera343.hotel.entity.Room;
import lera343.hotel.entity.Type;

import java.math.BigDecimal;
import java.util.HashSet;

public final class RoomStub {
    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final Integer PLACES_COUNT = 2;
    public static final BigDecimal PRICE = new BigDecimal("1233");
    public static final Type TYPE = TypeStub.getRandomType();
    public static Room getRandomRoom() {
        return Room.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .placesCount(PLACES_COUNT)
                .price(PRICE)
                .type(TYPE)
                .bookings(new HashSet<>())
                .build();
    }
    public static RoomRequest getRoomRequest() {
        var roomRequest = new RoomRequest();
        roomRequest.setName(NAME);
        roomRequest.setDescription(DESCRIPTION);
        roomRequest.setType(TYPE);
        roomRequest.setPrice(PRICE);
        roomRequest.setPlacesCount(PLACES_COUNT);
        return roomRequest;
    }
}
