package lera343.hotel.service.client.impls;

import lera343.hotel.dto.ClientRequest;
import lera343.hotel.dto.ClientResponse;
import lera343.hotel.dto.RoomResponse;
import lera343.hotel.entity.Client;
import lera343.hotel.mapper.ClientMapper;
import lera343.hotel.repository.ClientRepository;
import lera343.hotel.service.client.interfaces.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    @Override
    public List<ClientResponse> getAll() {
        var clients = clientRepository.findAll();
        return clients.stream().map(ClientResponse::mapToClientResponse).collect(Collectors.toList());
    }

    @Override
    public ClientResponse getById(Long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return ClientResponse.mapToClientResponse(client);
    }

    @Override
    public ClientResponse create(ClientRequest client) {
        var newClient = clientMapper.fromRequest(client);
        return ClientResponse.mapToClientResponse(clientRepository.save(newClient));
    }

    @Override
    public ClientResponse update(Long id, ClientRequest request) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        client.setDescription(request.getDescription());
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setNumber(request.getNumber());
        client.setPatronic(request.getPatronic());
        client.setSurname(request.getSurname());
        return ClientResponse.mapToClientResponse(clientRepository.save(client));
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

}
