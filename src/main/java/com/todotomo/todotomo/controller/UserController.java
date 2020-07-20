package com.todotomo.todotomo.controller;

import com.todotomo.todotomo.domain.user.User;
import com.todotomo.todotomo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) throws URISyntaxException {
        String email = user.getEmail();
        String name = user.getName();
        String password = user.getPassword();
        User saveUser = userService.save(email, name, password);
        String url = "/api/users/" + saveUser.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
