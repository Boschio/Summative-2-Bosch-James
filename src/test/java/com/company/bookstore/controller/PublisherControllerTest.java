package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PublisherController.class)
class PublisherControllerTest {

    @MockBean
    private PublisherRepository publisherRepo;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    private Publisher publisher;

    @BeforeEach
    public void setUp() throws Exception {
        publisher = new Publisher();
        publisher.setName("Tina Jo");
        publisher.setStreet("Wiley");
        publisher.setCity("Pembroke Pines");
        publisher.setState("FL");
        publisher.setPostalCode("12345");
        publisher.setPhone("111-111-1111");
        publisher.setEmail("tina@pub.com");
    }

    //should get all publishers
    @Test
    void getAllPublishers() throws Exception {
        mockMvc.perform(get("/publishers"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // should get publisher by id
    @Test
    void getPublisherById() throws Exception{
        mockMvc.perform(get("/publishers/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //should create publisher
    @Test
    void createPublisher() throws Exception {
        String input = mapper.writeValueAsString(publisher);

        mockMvc.perform(post("/publishers")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    //should update publisher
    @Test
    void updatePublisher() throws Exception{
        String input = mapper.writeValueAsString(publisher);

        mockMvc.perform(put("/publishers")
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    //should delete publisher by id
    @Test
    void deletePublisher() throws Exception {
        mockMvc.perform(delete("/publishers/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}