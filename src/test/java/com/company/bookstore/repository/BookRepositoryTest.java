package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    BookRepository bookRepository;


    private Author author;
    private Publisher publisher;
    private Book book;

    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

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

        publisher = new Publisher();
        publisher.setName("Tina Jo");
        publisher.setStreet("Wiley");
        publisher.setCity("Pembroke Pines");
        publisher.setState("FL");
        publisher.setPostalCode("12345");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("tina@pub.com");

        publisher = publisherRepository.save(publisher);

        book = new Book();
        book.setIsbn("5583169494121");
        book.setPublishDate(LocalDate.of(2001,1,1));
        book.setAuthorId(author.getId());
        book.setTitle("Testing For Dummies");
        book.setPublisherId(publisher.getId());
        book.setPrice(BigDecimal.valueOf(55.55));

        book = bookRepository.save(book);
    }

    @Test
    void createBook(){
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertEquals(book2.get(), book);
    }

    @Test
    void findBookByBookId() {
        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertEquals(book2.get(), book);
    }

    @Test
    void findAllBooks(){
        List<Book> books = bookRepository.findAll();
        assertEquals(1,books.size());
    }

    @Test
    void updateBook(){
        book.setTitle("UPDATED");

        bookRepository.save(book);

        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertEquals(book2.get(), book);
    }

    @Test
    void deleteBookById(){
        bookRepository.deleteById(book.getId());

        Optional<Book> book2 = bookRepository.findById(book.getId());
        assertFalse(book2.isPresent());
    }
    @Test
    void findBooksByAuthorId() {
        List<Book> books = bookRepository.findByAuthorId(book.getAuthorId());
        assertEquals(1, books.size());
    }
}