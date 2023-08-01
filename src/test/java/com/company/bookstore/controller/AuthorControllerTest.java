package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @MockBean
    private AuthorRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    private Author author;


    @BeforeEach
    public void setUp() throws Exception {
        author = new Author();
        author.setFirstName("James");
        author.setLastName("Bosch");
        author.setStreet("55 Broadway Ave.");
        author.setCity("Manhattan");
        author.setState("NY");
        author.setPostalCode("10001");
        author.setPhone("123-456-7890");
        author.setEmail("Test@test.com");
        author.setId(1);
    }
    @Test
    void shouldAddAuthor() throws Exception {
        String inputJson = mapper.writeValueAsString(author);

        mockMvc.perform(post("/authors")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAuthorById() throws Exception {
        mockMvc.perform(get("/authors/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateAuthor() throws Exception {
        author.setCity("Bronx");
        String inputJson = mapper.writeValueAsString(author);

        mockMvc.perform(put("/authors")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldDeleteAuthor() throws Exception {
        mockMvc.perform(delete("/authors/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
