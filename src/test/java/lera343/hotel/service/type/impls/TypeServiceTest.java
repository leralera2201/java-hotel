package lera343.hotel.service.type.impls;

import lera343.hotel.entity.Type;
import lera343.hotel.mapper.TypeMapper;
import lera343.hotel.repository.TypeRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypeServiceTest {
    private TypeService service;
    @Mock
    private TypeRepository typeRepository;
    @Mock
    private TypeMapper typeMapper;

    @BeforeEach
    void setup() {
        service = new TypeService(typeRepository, typeMapper);
    }

    @Test
    void getSuccessful() {
        List<Type> list = new ArrayList<Type>();
        var type = TypeStub.getRandomType();
        list.add(type);
        list.add(type);
        list.add(type);

        when(typeRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var type = TypeStub.getRandomType();
        when(typeRepository.findById(Mockito.any())).thenReturn(Optional.of(type));
        var byId = service.getById(TypeStub.ID);

        assertAll(() -> assertEquals(byId.getId(), type.getId()),
                () -> assertEquals(byId.getName(), type.getName()),
                () -> assertEquals(byId.getDescription(), type.getDescription()));

    }

    @Test
    void getFailedById() {
        when(typeRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(NoSuchElementException.class, () -> service.getById(TypeStub.ID));
        assertEquals(e.getMessage(), "No value present");
    }

    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Type.class);
        var type = TypeStub.getRandomType();
        when(typeMapper.fromRequest(Mockito.any())).thenReturn(type);
        when(typeRepository.save(Mockito.any())).thenReturn(TypeStub.getRandomType());
        var result = service.create(TypeStub.getTypeRequest());
        Mockito.verify(typeRepository, atLeast(1)).save(captor.capture());
        assertEquals(type, captor.getValue());
        assertEquals(type.getName(), result.getName());
        assertEquals(type.getId(), result.getId());
        assertEquals(type.getDescription(), result.getDescription());
    }

    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Type.class);
        var type = TypeStub.getRandomType();
        when(typeRepository.save(Mockito.any())).thenReturn(TypeStub.getRandomType());
        var result = service.update(TypeStub.ID, TypeStub.getTypeRequest());
        Mockito.verify(typeRepository, atLeast(1)).save(captor.capture());
        assertEquals(type, captor.getValue());
        assertEquals(type.getName(), result.getName());
        assertEquals(type.getId(), result.getId());
        assertEquals(type.getDescription(), result.getDescription());
    }

    @Test
    void deleteSuccessful() {
        service.delete(TypeStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(typeRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(TypeStub.ID, captor.getValue());
    }
}