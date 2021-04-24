package lera343.hotel.controller;

import lera343.hotel.dto.UserRequest;
import lera343.hotel.entity.security.Role;
import lera343.hotel.entity.security.User;
import lera343.hotel.service.security.SecurityService;
import lera343.hotel.service.user.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {
    private final SecurityService service;
//    @PostConstruct
//    public void createUser() {
//        var userRequest = new UserRequest();
//        userRequest.setUsername("user");
//        userRequest.setPassword("user");
//        service.createUser(userRequest, Role.ERole.ROLE_USER);
//        var adminRequest = new UserRequest();
//        adminRequest.setUsername("admin");
//        adminRequest.setPassword("admin");
//        service.createUser(adminRequest, Role.ERole.ROLE_ADMIN);
//    }
//

    @PostMapping("/createUser")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<User> registerUser(@RequestBody UserRequest userDto){
        return  ResponseEntity.ok(service.createUser(userDto, Role.ERole.ROLE_USER));
    }
    @PostMapping("/createAdmin")
    public ResponseEntity<User> registerAdmin(@RequestBody UserRequest userDto){
        return  ResponseEntity.ok(service.createUser(userDto, Role.ERole.ROLE_ADMIN));
    }
    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(service.createToken(userRequest));
    }

}
