package lera343.hotel.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "room", fetch = EAGER, cascade = CascadeType.ALL)
    private Set<Booking> bookings;
}
