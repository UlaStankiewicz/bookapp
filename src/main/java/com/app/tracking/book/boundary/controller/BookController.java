package com.app.tracking.book.boundary.controller;

import com.app.tracking.book.boundary.common.EntityNotFoundException;
import com.app.tracking.book.boundary.dto.BookRequestDTO;
import com.app.tracking.book.boundary.dto.BookResponseDTO;
import com.app.tracking.book.boundary.dto.BookUpdateRequestDTO;
import com.app.tracking.book.boundary.dto.CommentRequestDTO;
import com.app.tracking.book.internal.service.BookService;
import com.app.tracking.book.internal.service.CommentService;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  private static final Logger LOGGER = LogManager.getLogger(BookController.class);

  private BookService bookService;
  private CommentService commentService;

  public BookController(BookService bookService, CommentService commentService) {
    this.bookService = bookService;
    this.commentService = commentService;
  }

  @PostMapping("/books")
  public Long createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
    return bookService.createBook(bookRequestDTO);
  }

  @GetMapping("/books")
  public List<BookResponseDTO> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/books/{id}")
  public BookResponseDTO getById(@PathVariable Long id) throws EntityNotFoundException {
    return bookService.getBookWithComments(id);
  }

  @DeleteMapping("/books/{id}")
  public void removeBook(@PathVariable Long id) throws EntityNotFoundException {
    bookService.removeBookById(id);
  }

  @PutMapping("/books/{id}")
  public void editBook(@PathVariable Long id, @Valid @RequestBody BookUpdateRequestDTO bookDTO) throws EntityNotFoundException {
    bookService.edit(id, bookDTO);
  }

  @PostMapping("/books/{id}/comments")
  public Long createComment(@PathVariable Long id,
      @Valid @RequestBody CommentRequestDTO commentRequestDTO) throws EntityNotFoundException {
    return commentService.createComment(id, commentRequestDTO);
  }

}
