package com.dio.blogpessoal.service;

import com.dio.blogpessoal.model.User;
import com.dio.blogpessoal.model.UserLogin;
import com.dio.blogpessoal.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    public List<User> listAllUsers () {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Optional <UserLogin> loginUser(@RequestBody Optional<UserLogin> userLogin) {
        return userSecurityService.authenticateUser(userLogin);
    }

    @Override
    public Optional<User> createUser(User user) {
        return userSecurityService.createUser(user);
    }

    @Override
    public Optional<User> updateUser(@RequestBody @Valid User user) {
        return userSecurityService.updateUser(user);
    }


}
