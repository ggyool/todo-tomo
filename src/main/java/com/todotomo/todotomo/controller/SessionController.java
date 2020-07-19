package com.todotomo.todotomo.controller;

import com.todotomo.todotomo.domain.user.User;
import com.todotomo.todotomo.dto.SessionRequestDto;
import com.todotomo.todotomo.dto.SessionResponseDto;
import com.todotomo.todotomo.security.jwt.JwtFactory;
import com.todotomo.todotomo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RequestMapping(path="/session")
@RestController
public class SessionController {

    private final UserService userService;
    private final JwtFactory jwtFactory;

    @PostMapping
    public ResponseEntity<SessionResponseDto> save(
            @RequestBody SessionRequestDto sessionRequestDto
    ) throws URISyntaxException {


        String email = sessionRequestDto.getEmail();
        String password = sessionRequestDto.getPassword();
        User user = userService.authenticate(email, password);

        String url = "/session";
        String accessToken = jwtFactory.generateToken(user.getId(), user.getName());
        SessionResponseDto sessionResponseDto = SessionResponseDto.builder().accessToken(accessToken).build();
        return ResponseEntity.created(new URI(url)).body(sessionResponseDto);
    }

}
