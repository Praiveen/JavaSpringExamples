package com.example.prak6.controllers;

import com.example.prak6.entities.*;
import com.example.prak6.repositories.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public GraphQLController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public Book bookById(@Argument("id") Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Author authorById(@Argument("id") Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public Iterable<Author> allAuthors() {
        return authorRepository.findAll();
    }

    @MutationMapping
    public Book addBook(@Argument("name") String name, @Argument("genre") String genre, @Argument("authorId") Long authorId) {
        Author author = authorRepository.findById(authorId)
            .orElseThrow(() -> new RuntimeException("Author not found"));
        
        Book book = new Book();
        book.setName(name);
        book.setGenre(genre);
        book.setAuthor(author);
        
        return bookRepository.save(book);
    }

    @MutationMapping
    public Author addAuthor(@Argument("name") String name) {
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }


    @MutationMapping
    public boolean deleteAuthor(@Argument("id") Long id) {
        authorRepository.deleteById(id); 
        return true; 
    }

    @MutationMapping
    public boolean deleteBook(@Argument("id") Long id) {
        bookRepository.deleteById(id); 
        return true; 
    }

    
}
