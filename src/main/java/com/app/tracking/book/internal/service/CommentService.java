package com.app.tracking.book.internal.service;

import com.app.tracking.book.boundary.common.EntityNotFoundException;
import com.app.tracking.book.boundary.dto.CommentRequestDTO;
import com.app.tracking.book.internal.entity.Book;
import com.app.tracking.book.internal.entity.Comment;
import com.app.tracking.book.internal.repository.BookRepository;
import com.app.tracking.book.internal.repository.CommentRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

  private static final String BOOK_NOT_FOUND = "Book with id=%s not found";

  private CommentRepository commentRepository;
  private BookRepository bookRepository;

  public CommentService(CommentRepository commentRepository, BookRepository bookRepository) {
    this.commentRepository = commentRepository;
    this.bookRepository = bookRepository;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void deleteAllByBookId(Long bookId) {
    commentRepository.deleteAllByBookId(bookId);
  }

  public Long createComment(Long bookId, CommentRequestDTO commentRequestDTO) throws EntityNotFoundException {
    Optional<Book> book = getBookById(bookId);
    if(book.isPresent()) {
      Comment comment = new Comment(commentRequestDTO.getDescription(), book.get());
      commentRepository.save(comment);
      return comment.getId();
    }
    throw new EntityNotFoundException(String.format(BOOK_NOT_FOUND, bookId));
  }

  public Optional<Book> getBookById(Long id) {
    return bookRepository.findById(id);
  }

}
