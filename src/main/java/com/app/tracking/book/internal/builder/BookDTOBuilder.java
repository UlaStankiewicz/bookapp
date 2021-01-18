package com.app.tracking.book.internal.builder;

import com.app.tracking.book.boundary.dto.BookResponseDTO;
import com.app.tracking.book.internal.entity.Book;
import com.app.tracking.book.internal.mapper.BookMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookDTOBuilder {

  private CommentDTOBuilder commentDTOBuilder;
  private BookMapper bookMapper;

  public BookDTOBuilder(CommentDTOBuilder commentDTOBuilder, BookMapper bookMapper) {
    this.commentDTOBuilder = commentDTOBuilder;
    this.bookMapper = bookMapper;
  }

  public List<BookResponseDTO> buildBooks(List<Book> books) {
    return books.stream().map(this::buildBook).collect(Collectors.toList());
  }

  public BookResponseDTO buildBook(Book book) {
    BookResponseDTO responseDTO = bookMapper.fromEntityToResponse(book);
    responseDTO.setComments(commentDTOBuilder.findFiveNewestComments(book.getComments()));
    return responseDTO;
  }

}
