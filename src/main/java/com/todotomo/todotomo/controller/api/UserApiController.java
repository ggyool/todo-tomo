package com.todotomo.todotomo.controller.api;

import com.todotomo.todotomo.domain.user.User;
import com.todotomo.todotomo.dto.UserResponseDto;
import com.todotomo.todotomo.service.CustomUserDetailService;
import com.todotomo.todotomo.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping
    public UserResponseDto find(Authentication authentication){
        Claims claims = (Claims)authentication.getPrincipal();
        Long id = claims.get("id", Long.class);
        User user = userService.findById(id);
        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
