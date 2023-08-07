package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {
    @Autowired
    PublisherRepository publisherRepo;

    //Get all publishers
    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers(){
        return publisherRepo.findAll();
    }

    //Get publisher by id
    @GetMapping("/publishers/{id}")
    public Publisher getPublisherById(@PathVariable Integer id){
        Optional<Publisher> publisher = publisherRepo.findById(id);
        if(publisher.isPresent()){
           return publisher.get();
        }else{
            return null;
        }
    }

    // Create new publisher
    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher createPublisher(@RequestBody Publisher publisher){
        return publisherRepo.save(publisher);
    }

    //update publisher
    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher){
        publisherRepo.save(publisher);
    }

    //delete publisher by id
    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable Integer id){
        publisherRepo.deleteById(id);
    }
}
