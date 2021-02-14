package lera343.hotel.service.client.interfaces;

import lera343.hotel.entity.Client;

import java.util.List;

public interface IClientService {
    List<Client> getAll();
    Client getById(Long id);
    Client create(Client client);
    Client update(Long id, Client client);
    void delete(Long id);
}
