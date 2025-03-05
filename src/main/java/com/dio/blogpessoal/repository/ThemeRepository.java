package com.dio.blogpessoal.repository;

import com.dio.blogpessoal.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    public List<Theme> findAllByDescriptionContainingIgnoreCase(@Param("description") String description);
}
