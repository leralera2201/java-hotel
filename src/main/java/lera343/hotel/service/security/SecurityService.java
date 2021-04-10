package lera343.hotel.service.security;

import lera343.hotel.dto.UserRequest;
import lera343.hotel.entity.security.Role;
import lera343.hotel.entity.security.User;
import lera343.hotel.repository.RoleRepository;
import lera343.hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserRequest userRequest, Role.ERole role) {
        var roles = new HashSet<Role>();
        var build = Role.builder().name(role).build();
        roles.add(build);
        var user = User.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(roles)
                .build();
        roleRepository.save(build);
        return userRepository.save(user);
    }
}
