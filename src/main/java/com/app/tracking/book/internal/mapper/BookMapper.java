package com.app.tracking.book.internal.mapper;

import com.app.tracking.book.boundary.dto.BookRequestDTO;
import com.app.tracking.book.boundary.dto.BookResponseDTO;
import com.app.tracking.book.internal.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

  public Book fromRequestToEntity(BookRequestDTO bookRequestDTO) {
    return new Book(bookRequestDTO.getTitle(), bookRequestDTO.getAuthor(), bookRequestDTO.getISBN(),
        bookRequestDTO.getNumberPages(), bookRequestDTO.getRating());
  }

  public BookResponseDTO fromEntityToResponse(Book book) {
    return new BookResponseDTO(book.getId(), book.getTitle(),
        book.getAuthor(), book.getISBN(), book.getNumberPages(), book.getRating());
  }

}
