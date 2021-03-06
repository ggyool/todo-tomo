package com.todotomo.todotomo.controller.api;

import com.todotomo.todotomo.domain.user.User;
import com.todotomo.todotomo.dto.UserResponseDto;
import com.todotomo.todotomo.service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/users")
public class UserApiController {

    private final UserService userService;

    @GetMapping
    @ApiOperation(value="유저정보 가져오기 (token)")
    public UserResponseDto find(Authentication authentication){
        Claims claims = (Claims)authentication.getPrincipal();
        Long id = claims.get("id", Long.class);
        User user = userService.findById(id);
        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    @PostMapping
    @ApiOperation(value="유저 생성 (email, name, paassword)")
    public ResponseEntity<?> save(@RequestBody User user) throws URISyntaxException {
        String email = user.getEmail();
        String name = user.getName();
        String password = user.getPassword();
        User saveUser = userService.save(email, name, password);
        String url = "/api/users/" + saveUser.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
