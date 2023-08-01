package com.company.bookstore.repository;

import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublisherRepositoryTest {
    @Autowired
    PublisherRepository pubRepo;

    @Autowired
    BookRepository bookRepo;

    @BeforeEach
    void setUp() {
        pubRepo.deleteAll();
        bookRepo.deleteAll();
    }

    //Should get all publishers
    @Test
    void getAllPublishers(){
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Tina Jo");
        publisher.setStreetName("Wiley");
        publisher.setCityName("Pembroke Pines");
        publisher.setState("FL");
        publisher.setPostalCode("12345");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("tina@pub.com");
        publisher.setBooks(new HashSet<Book>());

        publisher = pubRepo.save(publisher);

        Publisher publisher2 = new Publisher();
        publisher2.setPublisherName("May Jo");
        publisher2.setStreetName("Wiley");
        publisher2.setCityName("Pembroke Pines");
        publisher2.setState("FL");
        publisher2.setPostalCode("54321");
        publisher2.setPhone("222-222-2222");
        publisher2.setEmail("may@pub.com");
        publisher2.setBooks(new HashSet<Book>());

        publisher2 = pubRepo.save(publisher2);

        List<Publisher> publishers = pubRepo.findAll();
        assertEquals(2,publishers.size());
    }

    //Should get all publishers  by id
    @Test
    void getAllPublishersById(){
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Tina Jo");
        publisher.setStreetName("Wiley");
        publisher.setCityName("Pembroke Pines");
        publisher.setState("FL");
        publisher.setPostalCode("12345");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("tina@pub.com");
        publisher.setBooks(new HashSet<Book>());

        publisher = pubRepo.save(publisher);

        Optional<Publisher> publisher2 = pubRepo.findById(publisher.getPublisherId());
        assertEquals(publisher2.get(),publisher);
    }

    //Should create a publisher
    @Test
    void createPublisher() {
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Tina Jo");
        publisher.setStreetName("Wiley");
        publisher.setCityName("Pembroke Pines");
        publisher.setState("FL");
        publisher.setPostalCode("12345");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("tina@pub.com");
        publisher.setBooks(new HashSet<Book>());

        publisher = pubRepo.save(publisher);

        Optional<Publisher> publisher2 = pubRepo.findById(publisher.getPublisherId());
        assertEquals(publisher2.get(), publisher);
    }

   //Should update existing publisher
   @Test
   void updatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Tina Jo");
        publisher.setStreetName("Wiley");
        publisher.setCityName("Pembroke Pines");
        publisher.setState("FL");
        publisher.setPostalCode("12345");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("tina@pub.com");
        publisher.setBooks(new HashSet<Book>());

        publisher = pubRepo.save(publisher);

        publisher.setPublisherName("UPDATED");

        pubRepo.save(publisher);

        Optional<Publisher> publisher2 = pubRepo.findById(publisher.getPublisherId());
        assertEquals(publisher2.get(),publisher);
   }

   //Should delete publisher by id
   @Test
    void deletePublisher(){
        Publisher publisher = new Publisher();
        publisher.setPublisherName("Tina Jo");
        publisher.setStreetName("Wiley");
        publisher.setCityName("Pembroke Pines");
        publisher.setState("FL");
        publisher.setPostalCode("12345");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("tina@pub.com");
        publisher.setBooks(new HashSet<Book>());

        publisher = pubRepo.save(publisher);

        pubRepo.deleteById(publisher.getPublisherId());

        Optional<Publisher> publisher2 = pubRepo.findById(publisher.getPublisherId());
        assertFalse(publisher2.isPresent());
    }
}