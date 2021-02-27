package lera343.hotel.dto;

import lera343.hotel.entity.Type;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomRequest {
    private String name;
    private String description;
    private Integer placesCount;
    private BigDecimal price;
    private Type type;
}
