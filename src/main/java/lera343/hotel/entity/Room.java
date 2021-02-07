package lera343.hotel.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Room {
    @Id
    private Long id;
    private String name;
    private String description;
    private Integer placesCount;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @OneToMany(mappedBy = "room")
    private Set<Booking> bookings;
}
