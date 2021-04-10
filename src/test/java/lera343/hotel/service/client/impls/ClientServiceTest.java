package lera343.hotel.service.client.impls;

import lera343.hotel.entity.Client;
import lera343.hotel.mapper.ClientMapper;
import lera343.hotel.repository.ClientRepository;
import lera343.hotel.stubs.ClientStub;
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
class ClientServiceTest {
    private ClientService service;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;

    @BeforeEach
    void setup() {
        service = new ClientService(clientRepository, clientMapper);
    }

    @Test
    void getSuccessful() {
        List<Client> list = new ArrayList<Client>();
        var client = ClientStub.getRandomClient();
        list.add(client);
        list.add(client);
        list.add(client);

        when(clientRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var client = ClientStub.getRandomClient();
        when(clientRepository.findById(Mockito.any())).thenReturn(Optional.of(client));
        var byId = service.getById(ClientStub.ID);

        assertAll(() -> assertEquals(byId.getId(), client.getId()),
                () -> assertEquals(byId.getName(), client.getName()),
                () -> assertEquals(byId.getEmail(), client.getEmail()),
                () -> assertEquals(byId.getNumber(), client.getNumber()),
                () -> assertEquals(byId.getSurname(), client.getSurname()),
                () -> assertEquals(byId.getPatronic(), client.getPatronic()),
                () -> assertEquals(byId.getDescription(), client.getDescription()));

    }

    @Test
    void getFailedById() {
        when(clientRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        var e = assertThrows(NoSuchElementException.class, () -> service.getById(ClientStub.ID));
        assertEquals(e.getMessage(), "No value present");
    }

    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Client.class);
        var client = ClientStub.getRandomClient();
        when(clientMapper.fromRequest(Mockito.any())).thenReturn(client);
        when(clientRepository.save(Mockito.any())).thenReturn(ClientStub.getRandomClient());
        var result = service.create(ClientStub.getClientRequest());
        Mockito.verify(clientRepository, atLeast(1)).save(captor.capture());
        assertEquals(client, captor.getValue());
        assertEquals(client.getName(), result.getName());
        assertEquals(client.getId(), result.getId());
        assertEquals(client.getEmail(), result.getEmail());
        assertEquals(client.getSurname(), result.getSurname());
    }

    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Client.class);
        var client = ClientStub.getRandomClient();
        when(clientRepository.save(Mockito.any())).thenReturn(ClientStub.getRandomClient());
        var result = service.update(ClientStub.ID, ClientStub.getClientRequest());
        Mockito.verify(clientRepository, atLeast(1)).save(captor.capture());
        assertEquals(client, captor.getValue());
        assertEquals(client.getName(), result.getName());
    }

    @Test
    void deleteSuccessful() {
        service.delete(ClientStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(clientRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(ClientStub.ID, captor.getValue());
    }

}