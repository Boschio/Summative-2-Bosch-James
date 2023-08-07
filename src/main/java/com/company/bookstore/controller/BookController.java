package com.company.bookstore.controller;

import com.company.bookstore.model.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository repo;

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return repo.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Integer id) {
        Optional<Book> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        }
        return null;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    @PutMapping("/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book) {
        repo.save(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Integer id) {
        repo.deleteById(id);
    }

    @GetMapping("/books/author/{id}")
    public List<Book> getAllBooksByAuthor(@PathVariable Integer id) {
        return repo.findByAuthorId(id);
    }

}
