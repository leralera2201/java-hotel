package lera343.hotel.stubs;

import lera343.hotel.dto.TypeRequest;
import lera343.hotel.entity.Type;

import java.util.HashSet;

public final class TypeStub {
    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static Type getRandomType() {
        return Type.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .rooms(new HashSet<>())
                .build();
    }
    public static TypeRequest getTypeRequest() {
        var typeRequest = new TypeRequest();
        typeRequest.setName(NAME);
        typeRequest.setDescription(DESCRIPTION);
        return typeRequest;
    }
}
