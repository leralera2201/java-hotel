package lera343.hotel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Client {
    @Id
    private String id;
    private String name;
    private String surname;
    private String patronic;
    private String number;
    private String email;
    private String description;
    @OneToMany(mappedBy = "client")
    private Set<Booking> bookings;
}
