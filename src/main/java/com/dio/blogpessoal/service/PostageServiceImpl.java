package com.dio.blogpessoal.service;

import com.dio.blogpessoal.model.Postage;
import com.dio.blogpessoal.repository.PostageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostageServiceImpl implements PostageService{

    @Autowired
    private PostageRepository postageRepository;

    @Override
    public List<Postage> listAllPosts(){
        return postageRepository.findAll();
    }

    @Override
    public Optional<Postage> findById(Long id) {
        return postageRepository.findById(id);
    }

    @Override
    public List<Postage> findByTitle(String title) {
        var listTitle = postageRepository.findAllByTitleContainingIgnoreCase(title);
        if(listTitle.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return listTitle;
    }

    @Override
    public Postage createPost(@RequestBody @Valid Postage postage) {
        return postageRepository.save(postage);
    }

    @Override
    public Postage updatePost(@RequestBody @Valid Postage postage){

        if(postageRepository.existsById(postage.getId())){
            return postageRepository.save(postage);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post does not exist", null);
    }

    @Override
    public void deletePost(@PathVariable Long id){
        Optional<Postage> postage = postageRepository.findById(id);

        if(postage.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        postageRepository.deleteById(id);
    }
}
