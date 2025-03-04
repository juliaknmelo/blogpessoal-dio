package com.dio.blogpessoal.service;

import com.dio.blogpessoal.model.Postage;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


public interface PostageService {

    List<Postage> listAllPosts();

    Optional<Postage> findById(Long id);

    List<Postage> findByTitle(@PathVariable String title);

    Postage createPost(@RequestBody @Valid Postage postage);

    Postage updatePost(@RequestBody @Valid Postage postage);

    void deletePost(@PathVariable Long id);
}
