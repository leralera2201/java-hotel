package lera343.hotel.service.room.impls;

import lera343.hotel.entity.Room;
import lera343.hotel.mapper.RoomMapper;
import lera343.hotel.repository.RoomRepository;
import lera343.hotel.stubs.RoomStub;
import lera343.hotel.stubs.TypeStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {
    private RoomService service;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomMapper roomMapper;

    @BeforeEach
    void setup() {
        service = new RoomService(roomRepository, roomMapper);
    }

    @Test
    void getSuccessful() {
        List<Room> list = new ArrayList<Room>();
        var room = RoomStub.getRandomRoom();
        list.add(room);
        list.add(room);
        list.add(room);

        when(roomRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulByTypeId() {
        List<Room> list = new ArrayList<Room>();
        var room = RoomStub.getRandomRoom();
        list.add(room);
        list.add(room);
        list.add(room);

        when(roomRepository.findRoomsByType_Id(TypeStub.ID)).thenReturn(list);
        var getAll = service.getRoomsByTypeId(TypeStub.ID);
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var room = RoomStub.getRandomRoom();
        when(roomRepository.findById(Mockito.any())).thenReturn(Optional.of(room));
        var byId = service.getById(RoomStub.ID);

        assertAll(() -> assertEquals(byId.getId(), room.getId()),
                () -> assertEquals(byId.getName(), room.getName()),
                () -> assertEquals(byId.getDescription(), room.getDescription()),
                () -> assertEquals(byId.getPrice(), room.getPrice()),
                () -> assertEquals(byId.getPlacesCount(), room.getPlacesCount()),
                () -> assertEquals(byId.getType().getId(), room.getType().getId()) );
    }


    @Test
    void getFailedById() {
        when(roomRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(NoSuchElementException.class, () -> service.getById(RoomStub.ID));
        assertEquals(e.getMessage(), "No value present");
    }

    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Room.class);
        var room = RoomStub.getRandomRoom();
        when(roomMapper.fromRequest(Mockito.any())).thenReturn(room);
        when(roomRepository.save(Mockito.any())).thenReturn(RoomStub.getRandomRoom());
        var result = service.create(RoomStub.getRoomRequest());
        Mockito.verify(roomRepository, atLeast(1)).save(captor.capture());
        assertEquals(room, captor.getValue());
        assertEquals(room.getName(), result.getName());
    }

    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Room.class);
        var room = RoomStub.getRandomRoom();
        when(roomRepository.save(Mockito.any())).thenReturn(RoomStub.getRandomRoom());
        var result = service.update(RoomStub.ID, RoomStub.getRoomRequest());
        Mockito.verify(roomRepository, atLeast(1)).save(captor.capture());
        assertEquals(room, captor.getValue());
        assertEquals(room.getName(), result.getName());
    }

    @Test
    void deleteSuccessful() {
        service.delete(RoomStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(roomRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(RoomStub.ID, captor.getValue());
    }

}