package com.dio.blogpessoal.controller;

import com.dio.blogpessoal.model.Theme;
import com.dio.blogpessoal.repository.ThemeRepository;
import com.dio.blogpessoal.service.ThemeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/themes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping("/all")
    public ResponseEntity <List<Theme>> listAllThemes() {
        return ResponseEntity.ok(themeService.listAllThemes());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Theme> findById(@PathVariable Long id){
        return themeRepository.findById(id)
                .map(response -> ResponseEntity.ok(response)).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<Theme>> findByDescription(@PathVariable String description){
        return ResponseEntity.ok(themeService.findByDescription(description));
    }

    @PostMapping
    public ResponseEntity<Theme> createTheme(@RequestBody @Valid Theme theme) {
        return themeService.createTheme(theme)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Theme> updateTheme(@RequestBody @Valid Theme theme){
        return themeService.updateTheme(theme)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public void deleteTheme(@PathVariable Long id){
        themeService.deleteTheme(id);
    }
}
