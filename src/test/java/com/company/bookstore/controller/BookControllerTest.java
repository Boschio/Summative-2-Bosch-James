package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    private Book book;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        book = new Book();
        book.setAuthorId(1);
        book.setIsbn("5583169494121");
        book.setPrice(new BigDecimal(55.55));
        book.setTitle("Testing For Dummies");
        book.setPublisherId(1);
        book.setPublishDate(new Date(2001-01-01));
    }

    @Test
    void addBook() throws Exception {
        String input = mapper.writeValueAsString(book);

        mockMvc.perform(post("/books")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getBookById() throws Exception {
        mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateBook() throws Exception {
        book.setPrice(new BigDecimal(69.69));
        String inputJson = mapper.writeValueAsString(book);

        mockMvc.perform(put("/books")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBookById() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllBooksByAuthor() throws Exception {
        mockMvc.perform(get("/books/author/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}