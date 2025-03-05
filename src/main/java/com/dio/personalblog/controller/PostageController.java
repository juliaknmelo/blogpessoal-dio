package com.dio.personalblog.controller;

import com.dio.personalblog.model.Postage;
import com.dio.personalblog.service.PostageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostageController {

    @Autowired
    private PostageService postageService;


    @GetMapping("/all")
    public ResponseEntity <List<Postage>> listAllPosts() {
        return ResponseEntity.ok(postageService.listAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Postage> findById(@PathVariable Long id){
        return postageService.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public ResponseEntity <List<Postage>> findByTitle(@PathVariable String title){
        return ResponseEntity.ok(postageService.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Postage> createPost (@RequestBody @Valid Postage postage){
        return ResponseEntity.status(HttpStatus.OK)
                .body(postageService.createPost(postage));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Postage> updatePost(@RequestBody @Valid Postage postage){
        return ResponseEntity.status(HttpStatus.OK)
                .body(postageService.updatePost(postage));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postageService.deletePost(id);
    }

}
