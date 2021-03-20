package lera343.hotel.stubs;

import lera343.hotel.dto.ClientRequest;
import lera343.hotel.entity.Client;
import java.util.HashSet;

public final class ClientStub {
    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PATRONIC = "patronic";
    public static final String EMAIL = "email";
    public static final String NUMBER = "number";
    public static final String DESCRIPTION = "description";
    public static Client getRandomClient() {
        return Client.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .surname(SURNAME)
                .patronic(PATRONIC)
                .number(NUMBER)
                .email(EMAIL)
                .bookings(new HashSet<>())
                .build();
    }
    public static ClientRequest getClientRequest() {
        var clientRequest = new ClientRequest();
        clientRequest.setName(NAME);
        clientRequest.setDescription(DESCRIPTION);
        clientRequest.setEmail(EMAIL);
        clientRequest.setNumber(NUMBER);
        clientRequest.setPatronic(PATRONIC);
        clientRequest.setSurname(SURNAME);
        return clientRequest;
    }
}
