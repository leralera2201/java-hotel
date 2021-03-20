package lera343.hotel.repository;

import lera343.hotel.entity.Room;
import lera343.hotel.stubs.RoomStub;
import lera343.hotel.stubs.TypeStub;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoomRepositoryTest {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TestEntityManager entityManager;
    private Room expectedRoom;

    @BeforeEach
    void setUp() {
        expectedRoom = RoomStub.getRandomRoom();
        entityManager.persist(expectedRoom);
        entityManager.flush();
    }

    @Test
    void testFindRoomsByTypes() {
        var actualRooms = roomRepository.findRoomsByType_Id(TypeStub.ID);
        Assertions.assertThat(actualRooms).hasSize(1);
    }


}