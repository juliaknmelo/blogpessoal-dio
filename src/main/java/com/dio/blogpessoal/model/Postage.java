package com.dio.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "tb_postages")
public class Postage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 50, message = "The title field must be between 4 and 50 characters long.")
    private String title;

    @Size(min = 4, max = 500, message = "The text field must be between 4 and 500 characters long.")
    private String text;

    @UpdateTimestamp
    private LocalDateTime date;

    @ManyToOne
    @JsonIgnoreProperties("postage")
    private Theme theme;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Postage(Long id, String title, String text, LocalDateTime date, Theme theme) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.theme = theme;
    }

    public Postage() {
    }
}
