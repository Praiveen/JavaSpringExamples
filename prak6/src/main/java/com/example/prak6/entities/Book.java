package com.example.prak6.entities;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
