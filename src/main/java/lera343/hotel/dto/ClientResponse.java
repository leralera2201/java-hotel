package lera343.hotel.dto;

import lera343.hotel.entity.Client;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientResponse {
    private Long id;
    private String name;
    private String surname;
    private String patronic;
    private String number;
    private String email;
    private String description;

    public static ClientResponse mapToClientResponse(Client client) {
        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setId(client.getId());
        clientResponse.setDescription(client.getDescription());
        clientResponse.setName(client.getName());
        clientResponse.setSurname(client.getSurname());
        clientResponse.setPatronic(client.getPatronic());
        clientResponse.setEmail(client.getEmail());
        clientResponse.setNumber(client.getNumber());

        return clientResponse;
    }

}
