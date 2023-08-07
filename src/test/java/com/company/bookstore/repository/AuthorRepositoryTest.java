package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    Author author;
    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();

        author = new Author();
        author.setFirstName("James");
        author.setLastName("Bosch");
        author.setStreet("55 Broadway Ave.");
        author.setCity("Manhattan");
        author.setState("NY");
        author.setPostalCode("10001");
        author.setPhone("123-456-7890");
        author.setEmail("Test@test.com");

        author = authorRepository.save(author);
    }

    @Test
    public void createAuthor() {
        Optional<Author> author1 = authorRepository.findById(author.getId());

        assertEquals(author1.get(), author);
    }

    @Test
    public void getAuthorById() {
        Optional<Author> foundAuthor = authorRepository.findById(author.getId());

        assertEquals(foundAuthor.get(),author);
    }

    @Test
    public void getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();

        assertEquals(authorList.size(), 1);
    }

    @Test
    public void updateAuthor() {
        author.setCity("Bronx");
        author.setPostalCode("10463");

        authorRepository.save(author);

        Optional<Author> author1 = authorRepository.findById(author.getId());

        assertEquals(author1.get(), author);
    }

    @Test
    public void deleteAuthorById() {
        Author authorToDel = new Author();
        authorToDel.setFirstName("Delete");
        authorToDel.setLastName("This");
        authorToDel.setStreet("69 Delete Me");
        authorToDel.setCity("Erase");
        authorToDel.setState("ME");
        authorToDel.setPostalCode("99999");
        authorToDel.setPhone("123-456-7890");
        authorToDel.setEmail("delete@this.now");

        authorToDel = authorRepository.save(authorToDel);

        Optional<Author> author1 = authorRepository.findById(authorToDel.getId());

        assertEquals(author1.get(), authorToDel);

        authorRepository.deleteById(authorToDel.getId());

        author1 = authorRepository.findById(authorToDel.getId());

        assertFalse(author1.isPresent());
    }

}
