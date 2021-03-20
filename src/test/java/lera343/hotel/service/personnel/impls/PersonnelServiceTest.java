package lera343.hotel.service.personnel.impls;

import lera343.hotel.entity.Personnel;
import lera343.hotel.repository.PersonnelRepository;
import lera343.hotel.stubs.PersonnelStub;
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
class PersonnelServiceTest {
    private PersonnelService service;
    @Mock
    private PersonnelRepository personnelRepository;

    @BeforeEach
    void setup() {
        service = new PersonnelService(personnelRepository);
    }

    @Test
    void getSuccessful() {
        List<Personnel> list = new ArrayList<Personnel>();
        var personnel = PersonnelStub.getRandomPersonnel();
        list.add(personnel);
        list.add(personnel);
        list.add(personnel);

        when(personnelRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var personnel = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.findById(Mockito.any())).thenReturn(Optional.of(personnel));
        var byId = service.getById(PersonnelStub.ID);

        assertAll(() -> assertEquals(byId.getId(), personnel.getId()),
                () -> assertEquals(byId.getName(), personnel.getName()),
                () -> assertEquals(byId.getDescription(), personnel.getDescription()),
                () -> assertEquals(byId.getSurname(), personnel.getSurname()),
                () -> assertEquals(byId.getPatronic(), personnel.getPatronic()),
                () -> assertEquals(byId.getBirthday(), personnel.getBirthday()),
                () -> assertEquals(byId.getEmail(), personnel.getEmail()),
                () -> assertEquals(byId.getNumber(), personnel.getNumber()),
                () -> assertEquals(byId.getPosition(), personnel.getPosition()),
                () -> assertEquals(byId.getStartedWork(), personnel.getStartedWork()));
    }


    @Test
    void getFailedById() {
        when(personnelRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(NoSuchElementException.class, () -> service.getById(PersonnelStub.ID));
        assertEquals(e.getMessage(), "No value present");
    }

    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Personnel.class);
        var personnel = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.save(Mockito.any())).thenReturn(PersonnelStub.getRandomPersonnel());
        var result = service.create(PersonnelStub.getRandomPersonnel());
        Mockito.verify(personnelRepository, atLeast(1)).save(captor.capture());
        assertEquals(personnel, captor.getValue());
        assertEquals(personnel.getName(), result.getName());
    }

    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Personnel.class);
        var personnel = PersonnelStub.getRandomPersonnel();
        when(personnelRepository.save(Mockito.any())).thenReturn(PersonnelStub.getRandomPersonnel());
        var result = service.update(PersonnelStub.ID, PersonnelStub.getRandomPersonnel());
        Mockito.verify(personnelRepository, atLeast(1)).save(captor.capture());
        assertEquals(personnel, captor.getValue());
        assertEquals(personnel.getName(), result.getName());
    }

    @Test
    void deleteSuccessful() {
        service.delete(PersonnelStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(personnelRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(PersonnelStub.ID, captor.getValue());
    }
}