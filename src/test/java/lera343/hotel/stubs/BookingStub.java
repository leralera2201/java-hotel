package lera343.hotel.stubs;

import lera343.hotel.entity.Booking;
import lera343.hotel.entity.Client;
import lera343.hotel.entity.Room;

import java.util.Date;

public final class BookingStub {
    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final Client CLIENT = ClientStub.getRandomClient();
    public static final Room ROOM = RoomStub.getRandomRoom();
    public static final Date ARRIVALDATE = new Date();
    public static final Date DEPARTUREDATE = new Date();
    public static Booking getRandomBooking() {
        return Booking.builder()
                .id(ID)
                .description(DESCRIPTION)
                .arrivalDate(ARRIVALDATE)
                .departureDate(DEPARTUREDATE)
                .client(CLIENT)
                .room(ROOM)
                .build();
    }
}
