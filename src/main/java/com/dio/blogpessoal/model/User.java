package com.dio.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Email
    private String username;


    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("user")
    private List<Postage> postage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Email String getUsername() {
        return username;
    }

    public void setUsername(@Email String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Postage> getPostage() {
        return postage;
    }

    public void setPostage(List<Postage> postage) {
        this.postage = postage;
    }

    public User(Long id, String name, String username, String password, List<Postage> postage) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.postage = postage;
    }
}
