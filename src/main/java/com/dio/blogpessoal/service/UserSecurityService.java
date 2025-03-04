package com.dio.blogpessoal.service;

import com.dio.blogpessoal.model.User;
import com.dio.blogpessoal.model.UserLogin;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserSecurityService {

    Optional<User> createUser(User user);

    Optional<User> updateUser(User user);

    Optional<UserLogin> authenticateUser(Optional<UserLogin> userLogin);
}
