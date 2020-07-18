package com.todotomo.todotomo.controller;

import com.todotomo.todotomo.domain.user.Role;
import com.todotomo.todotomo.domain.user.User;
import com.todotomo.todotomo.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path="/users")
@RestController
public class UserController {


    private final UserService userService;

    @GetMapping
    public List<User> findAll(){
        List<User> users = userService.findAll();
        return users;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) throws URISyntaxException {
        String email = user.getEmail();
        String name = user.getName();
        String password = user.getPassword();
        User saveUser = userService.save(email, name, password);
        String url = "/api/v1/users/" + saveUser.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody User user){
        String email = user.getEmail();
        String name = user.getName();
        Role role = user.getRole();
        String password = user.getPassword();
        userService.update(id, email, name, password, role);
        return "{}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
            userService.delete(id);;
        return "{}";
    }


}
