package com.dio.personalblog.service;


import com.dio.personalblog.model.Theme;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


public interface ThemeService {


    List<Theme> listAllThemes();

    Theme findById(Long id);

    List<Theme> findByDescription(String description);

    Optional<Theme> createTheme(@RequestBody @Valid Theme theme);

    Optional<Theme> updateTheme(@RequestBody @Valid Theme theme);

    void deleteTheme(@PathVariable Long id);
}
