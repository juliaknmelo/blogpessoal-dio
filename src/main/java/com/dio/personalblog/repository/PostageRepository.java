package com.dio.personalblog.repository;

import com.dio.personalblog.model.Postage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostageRepository extends JpaRepository<Postage, Long> {

    public List<Postage> findAllByTitleContainingIgnoreCase(@Param("title") String title);
}
