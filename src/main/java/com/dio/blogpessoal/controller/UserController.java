package com.dio.blogpessoal.controller;

import com.dio.blogpessoal.model.User;
import com.dio.blogpessoal.model.UserLogin;
import com.dio.blogpessoal.repository.UserRepository;
import com.dio.blogpessoal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity <List<User>> listAllUsers() {
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity <User> findById(@PathVariable Long id){
        return userRepository.findById(id)
                .map(response -> ResponseEntity.ok(response)).
                orElse(ResponseEntity.notFound().build());
    }
     //PERGUNTAR SOBRE A SERVICE NÃO SER IGUAL A POSTAGE

    @PostMapping("/login")
    public ResponseEntity<Optional<UserLogin>> loginUser(@RequestBody Optional<UserLogin> userLogin){
        var login = userService.loginUser(userLogin);
        return ResponseEntity.status(HttpStatus.OK).body(login);
        /*return userService.loginUser(userLogin)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());*/

    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return userService.createUser(user)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user){
        return userService.updateUser(user)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
