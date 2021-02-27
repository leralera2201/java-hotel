package lera343.hotel.dto;

import lera343.hotel.entity.Type;
import lombok.Data;

@Data
public class TypeResponse {
    private Long id;
    private String name;
    private String description;

    public static TypeResponse mapToTypeResponse(Type type) {
        TypeResponse typeResponse = new TypeResponse();

        typeResponse.setId(type.getId());
        typeResponse.setDescription(type.getDescription());
        typeResponse.setName(type.getName());

        return typeResponse;
    }

}
