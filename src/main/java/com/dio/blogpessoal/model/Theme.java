package com.dio.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "tb_theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 50, message = "The description field must be between 4 and 50 characters long.")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("theme")
    private List<Postage> postage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 4, max = 50, message = "The description field must be between 4 and 50 characters long.") String getdescription() {
        return description;
    }

    public void setdescription(@Size(min = 4, max = 50, message = "The description field must be between 4 and 50 characters long.") String description) {
        this.description = description;
    }

    public List<Postage> getPostage() {
        return postage;
    }

    public void setPostage(List<Postage> postage) {
        this.postage = postage;
    }

    public Theme(Long id, String description, List<Postage> postage) {
        this.id = id;
        this.description = description;
        this.postage = postage;
    }

    public Theme() {
    }
}
