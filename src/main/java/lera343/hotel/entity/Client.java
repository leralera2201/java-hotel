package lera343.hotel.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String patronic;
    private String number;
    private String email;
    private String description;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "client", fetch = EAGER, cascade = CascadeType.ALL)
    private Set<Booking> bookings;
}
