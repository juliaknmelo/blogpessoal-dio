package com.dio.blogpessoal.service;

import com.dio.blogpessoal.model.User;
import com.dio.blogpessoal.model.UserLogin;
import com.dio.blogpessoal.repository.UserRepository;
import com.dio.blogpessoal.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
class UserServiceSecurityImpl implements UserSecurityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Optional<User> createUser(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent())
            return Optional.empty();

        user.setPassword(encryptPassword(user.getPassword()));

        return Optional.of(userRepository.save(user));

    }

    @Override
    public Optional<User> updateUser(User user) {

        if(userRepository.findById(user.getId()).isPresent()) {

            Optional<User> getUser = userRepository.findByUsername(user.getUsername());

            if ( (getUser.isPresent()) && (getUser.get().getId() != user.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already exits!", null);

            user.setPassword(encryptPassword(user.getPassword()));

            return Optional.ofNullable(userRepository.save(user));

        }

        return Optional.empty();

    }

    @Override
    public Optional<UserLogin> authenticateUser(Optional<UserLogin> userLogin) {

        var credencials = new UsernamePasswordAuthenticationToken(userLogin.get().getUsername(), userLogin.get().getPassword());

        Authentication authentication = authenticationManager.authenticate(credencials);

        if (authentication.isAuthenticated()) {

            Optional<User> user = userRepository.findByUsername(userLogin.get().getUsername());

            if (user.isPresent()) {

                userLogin.get().setId(user.get().getId());
                userLogin.get().setName(user.get().getName());
                userLogin.get().setToken(generateToken(userLogin.get().getUsername()));
                userLogin.get().setPassword("");

                return userLogin;
            }
        }

        return Optional.empty();

    }

    private String encryptPassword(String senha) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);

    }

    private String generateToken(String user) {
        return "Bearer " + jwtService.generateToken(user);
    }

}
