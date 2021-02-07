package lera343.hotel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Booking {
    @Id
    private String id;
    private String description;
    private Date arrivalDate;
    private Date departureDate;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
