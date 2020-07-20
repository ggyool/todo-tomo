package com.todotomo.todotomo.service;

import com.todotomo.todotomo.domain.user.Role;
import com.todotomo.todotomo.domain.user.User;
import com.todotomo.todotomo.domain.user.UserRepository;
import com.todotomo.todotomo.exception.PasswordMismatchedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
    }

    public User save(String email, String name, String password){
        userRepository.findByEmail(email)
                .ifPresent(user-> {
                    throw new IllegalArgumentException("해당 email이 존재합니다: " + user.getEmail());
                });

        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder().email(email).name(name).password(encodedPassword).role(Role.USER).build();
        return userRepository.save(user);
    }

    public User update(Long id, String email, String name, String password, Role role){
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        String encodedPassword = passwordEncoder.encode(password);
        user.update(email, name, encodedPassword, role);
        return user;
    }

    public User delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        userRepository.delete(user);
        return user;
    }

    public User authenticate(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException("해당 email이 존재하지 않습니다: " + email));
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordMismatchedException();
        }
        return user;
    }
}
