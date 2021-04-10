package lera343.hotel.entity.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
