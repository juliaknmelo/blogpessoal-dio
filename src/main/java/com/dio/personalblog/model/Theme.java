package com.dio.personalblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "tb_theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The description attribute is required ")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("theme")
    private List<Postage> postage;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
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
