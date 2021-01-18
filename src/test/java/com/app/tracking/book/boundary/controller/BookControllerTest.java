package com.app.tracking.book.boundary.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.app.tracking.book.boundary.dto.BookResponseDTO;
import com.app.tracking.book.internal.entity.Book;
import com.app.tracking.book.internal.service.BookService;
import com.app.tracking.book.internal.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private BookService bookService;

  @MockBean
  private CommentService commentService;

  @Test
  void createBook() throws Exception {
    Book book = new Book("Title", "Author", "123456789-1", 500, 4);
    mockMvc.perform(post("/books")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(book)))
        .andExpect(status().isOk());
  }

  @Test
  void createBook_whenRequiredFieldsNotFilled() throws Exception {
    Book book = new Book();
    mockMvc.perform(post("/books")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(book)))
        .andExpect(status().is4xxClientError());
  }

  @Test
  void getAllBooks() throws Exception {
    BookResponseDTO book = new BookResponseDTO(1L, "Title", "Author", "123456789-1", 500, 4);
    given(bookService.getAllBooks()).willReturn(Collections.singletonList(book));

    mockMvc.perform(get("/books")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(book.getId().intValue())))
        .andExpect(jsonPath("$[0].title", is(book.getTitle())))
        .andExpect(jsonPath("$[0].author", is(book.getAuthor())))
        .andExpect(jsonPath("$[0].isbn", is(book.getISBN())))
        .andExpect(jsonPath("$[0].numberPages", is(book.getNumberPages())))
        .andExpect(jsonPath("$[0].rating", is(book.getRating())));
  }

  @Test
  void getById() throws Exception {
    BookResponseDTO book = new BookResponseDTO(1L, "Title", "Author", "123456789-1", 500, 4);
    given(bookService.getBookWithComments(1L)).willReturn(book);

    mockMvc.perform(get("/books/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(book.getId().intValue())))
        .andExpect(jsonPath("$.title", is(book.getTitle())))
        .andExpect(jsonPath("$.author", is(book.getAuthor())))
        .andExpect(jsonPath("$.isbn", is(book.getISBN())))
        .andExpect(jsonPath("$.numberPages", is(book.getNumberPages())))
        .andExpect(jsonPath("$.rating", is(book.getRating())));
  }

  @Test
  void removeBook() throws Exception {
    mockMvc.perform(delete("/books/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  //TODO
  @Test
  void editBook() {
  }

  //TODO
  @Test
  void createComment() {
  }
}