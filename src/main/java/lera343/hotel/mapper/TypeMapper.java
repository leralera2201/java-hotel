package lera343.hotel.mapper;

import lera343.hotel.dto.TypeRequest;
import lera343.hotel.entity.Type;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;

@Component
public class TypeMapper {
    public Type fromRequest(TypeRequest request) {
        return Type.builder()
                .id(new Random().nextLong())
                .description(request.getDescription())
                .name(request.getName())
                .rooms(new HashSet<>())
                .build();
    }
}
