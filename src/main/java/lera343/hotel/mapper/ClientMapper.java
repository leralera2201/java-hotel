package lera343.hotel.mapper;

import lera343.hotel.dto.ClientRequest;
import lera343.hotel.entity.Client;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;

@Component
public class ClientMapper {
    public Client fromRequest(ClientRequest request) {
        return Client.builder()
                .id(new Random().nextLong())
                .name(request.getName())
                .surname(request.getSurname())
                .patronic(request.getPatronic())
                .number(request.getNumber())
                .email(request.getEmail())
                .description(request.getDescription())
                .bookings(new HashSet<>())
                .build();
    }
}
