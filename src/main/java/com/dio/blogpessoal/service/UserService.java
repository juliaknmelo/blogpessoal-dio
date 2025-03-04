package com.dio.blogpessoal.service;


import com.dio.blogpessoal.model.User;
import com.dio.blogpessoal.model.UserLogin;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> listAllUsers ();

    User findById(Long id);

    Optional<UserLogin> loginUser(Optional<UserLogin> userLogin);

    Optional<User> createUser(User user);

    Optional<User> updateUser(@RequestBody @Valid User user);
}
