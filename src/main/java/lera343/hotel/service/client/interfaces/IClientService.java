package lera343.hotel.service.client.interfaces;

import lera343.hotel.dto.ClientRequest;
import lera343.hotel.dto.ClientResponse;
import lera343.hotel.entity.Client;

import java.util.List;

public interface IClientService {
    List<ClientResponse> getAll();
    ClientResponse getById(Long id);
    ClientResponse create(ClientRequest client);
    ClientResponse update(Long id, ClientRequest client);
    void delete(Long id);
}
