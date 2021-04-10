package lera343.hotel.service.user;

import lera343.hotel.entity.security.UserDetailsImpl;
import lera343.hotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.fromUser(userRepository.findByUsername(username).orElseThrow());
    }

}
