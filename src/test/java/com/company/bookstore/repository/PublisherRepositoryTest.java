package com.company.bookstore.repository;

import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublisherRepositoryTest {
    @Autowired
    PublisherRepository pubRepo;

    @Autowired
    BookRepository bookRepo;

    Publisher publisher;
    Publisher publisher2;

    @BeforeEach
    void setUp() throws Exception {
        bookRepo.deleteAll();
        pubRepo.deleteAll();

        publisher = new Publisher();
        publisher.setName("Tina Jo");
        publisher.setStreet("Wiley");
        publisher.setCity("Pembroke Pines");
        publisher.setState("FL");
        publisher.setPostalCode("12345");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("tina@pub.com");

        publisher = pubRepo.save(publisher);

        publisher2 = new Publisher();
        publisher2.setName("May Jo");
        publisher2.setStreet("Wiley");
        publisher2.setCity("Pembroke Pines");
        publisher2.setState("FL");
        publisher2.setPostalCode("54321");
        publisher2.setPhone("222-222-2222");
        publisher2.setEmail("may@pub.com");

        publisher2 = pubRepo.save(publisher2);
    }

    //Should get all publishers
    @Test
    void getAllPublishers(){
        List<Publisher> publishers = pubRepo.findAll();
        assertEquals(2,publishers.size());
    }

    //Should get all publishers  by id
    @Test
    void getAllPublishersById(){
        Optional<Publisher> publisher1 = pubRepo.findById(publisher.getId());
        assertEquals(publisher1.get(),publisher);
    }

    //Should create a publisher
    @Test
    void createPublisher() {
        Optional<Publisher> publisher1 = pubRepo.findById(publisher.getId());
        assertEquals(publisher1.get(), publisher);
    }

   //Should update existing publisher
   @Test
   void updatePublisher() {
        publisher.setName("UPDATED");

        pubRepo.save(publisher);

        Optional<Publisher> publisher1 = pubRepo.findById(publisher.getId());
        assertEquals(publisher1.get(),publisher);
   }

   //Should delete publisher by id
   @Test
    void deletePublisher(){
        Publisher publisherToDel = new Publisher();
        publisherToDel.setName("Tina Jo");
        publisherToDel.setStreet("Wiley");
        publisherToDel.setCity("Pembroke Pines");
        publisherToDel.setState("FL");
        publisherToDel.setPostalCode("12345");
        publisherToDel.setPhone("111-111-1111");
        publisherToDel.setEmail("tina@pub.com");
        publisherToDel.setBooks(new HashSet<Book>());

        publisherToDel = pubRepo.save(publisherToDel);

        pubRepo.deleteById(publisherToDel.getId());

        Optional<Publisher> publisher1 = pubRepo.findById(publisherToDel.getId());
        assertFalse(publisher1.isPresent());
    }
}