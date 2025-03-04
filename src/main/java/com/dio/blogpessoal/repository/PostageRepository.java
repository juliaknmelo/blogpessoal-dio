package com.dio.blogpessoal.repository;

import com.dio.blogpessoal.model.Postage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostageRepository extends JpaRepository<Postage, Long> {

    public List<Postage> findAllByTitleContainingIgnoreCase(@Param("title") String title);
}
