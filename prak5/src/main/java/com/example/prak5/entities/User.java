package com.example.prak5.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String surename;
    private Integer age;
    private Integer course;

    public void printUser() {
        System.out.println("User: " + name);
    }
}
