package com.example.prak6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.prak6.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
