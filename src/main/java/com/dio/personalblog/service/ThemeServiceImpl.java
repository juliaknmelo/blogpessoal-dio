package com.dio.personalblog.service;

import com.dio.personalblog.model.Theme;
import com.dio.personalblog.repository.ThemeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;


    @Override
    public List<Theme> listAllThemes() {
        return themeRepository.findAll();
    }


    @Override
    public Theme findById(Long id){
        return themeRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }


    @Override
    public List<Theme> findByDescription(String description){
        var listDescription =  themeRepository.findAllByDescriptionContainingIgnoreCase(description);
        if(listDescription.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return listDescription;
    }


    @Override
    public Optional<Theme> createTheme(@RequestBody @Valid Theme theme) {
        return Optional.of(themeRepository.save(theme));
    }

    @Override
    public Optional<Theme> updateTheme(@RequestBody @Valid Theme theme) {

        if(themeRepository.existsById(theme.getId())){
            return Optional.of(themeRepository.save(theme));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theme does not exist", null);

    }
    @Override
    public void deleteTheme(@PathVariable Long id){
        Optional<Theme> theme = themeRepository.findById(id);

        if(theme.isEmpty()){
            throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        }
        themeRepository.deleteById(id);
    }


}
