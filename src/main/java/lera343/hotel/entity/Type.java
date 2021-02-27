package lera343.hotel.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static org.hibernate.annotations.FetchMode.SELECT;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    @Id
    private Long id;
    private String name;
    private String description;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "type", fetch = EAGER, cascade = CascadeType.ALL)
    private Set<Room> rooms;
}
