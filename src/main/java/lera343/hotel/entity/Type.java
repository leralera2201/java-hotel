package lera343.hotel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Type {
    @Id
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "type")
    private Set<Room> rooms;
}
