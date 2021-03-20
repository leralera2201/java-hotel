package lera343.hotel.service.client.impls;

import lera343.hotel.dto.ClientRequest;
import lera343.hotel.dto.ClientResponse;
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
        Optional<Client> result = clientRepository.findById(id);
        if (result.isPresent()) {
            return ClientResponse.mapToClientResponse(result.get());
        } else {
            return ClientResponse.mapToClientResponse(result.orElseThrow());
        }
    }

    @Override
    public ClientResponse create(ClientRequest client) {
        var newClient = clientMapper.fromRequest(client);
        return ClientResponse.mapToClientResponse(clientRepository.save(newClient));
    }

    @Override
    public ClientResponse update(Long id, Client client) {
        return ClientResponse.mapToClientResponse(clientRepository.save(client));
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

}
