package com.app.tracking.book.internal.service;

import com.app.tracking.book.boundary.common.EntityNotFoundException;
import com.app.tracking.book.boundary.dto.BookRequestDTO;
import com.app.tracking.book.boundary.dto.BookResponseDTO;
import com.app.tracking.book.boundary.dto.BookUpdateRequestDTO;
import com.app.tracking.book.internal.mapper.BookMapper;
import com.app.tracking.book.internal.repository.BookRepository;
import com.app.tracking.book.internal.builder.BookDTOBuilder;
import com.app.tracking.book.internal.entity.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

  private static final Logger LOGGER = LogManager.getLogger(BookService.class);
  private static final int pageSizeBook = 1000;
  private static final String BOOK_NOT_FOUND = "Book with id=%s not found";

  private BookRepository bookRepository;
  private BookDTOBuilder bookDTOBuilder;
  private BookMapper bookMapper;
  private CommentService commentService;

  public BookService(BookRepository bookRepository, BookDTOBuilder bookDTOBuilder, BookMapper bookMapper, CommentService commentService) {
    this.bookRepository = bookRepository;
    this.bookDTOBuilder = bookDTOBuilder;
    this.bookMapper = bookMapper;
    this.commentService = commentService;
  }

  public Long createBook(BookRequestDTO bookRequestDTO) {
    Book book = bookMapper.fromRequestToEntity(bookRequestDTO);
    Book created = bookRepository.save(book);
    return created.getId();
  }

  @Transactional(readOnly = true)
  public List<BookResponseDTO> getAllBooks() {

    long totalBooks = bookRepository.count();
    LOGGER.info("Total books in database: " + totalBooks);

    List<BookResponseDTO> allBooks = new ArrayList<>();
    for(int chunk = 0; chunk < getChunkSize((int) totalBooks); chunk++) {
      LOGGER.info("processing chunk number: " + (chunk + 1) + " start.");
      allBooks.addAll(getChunkOfBooks(chunk));
      LOGGER.info("processing chunk number: " +  (chunk + 1) + " end.");
    }
    return allBooks;
  }

  private List<BookResponseDTO> getChunkOfBooks(int chunk) {
    List<Book> books = bookRepository.findBooks(PageRequest.of(chunk, pageSizeBook));
    return bookDTOBuilder.buildBooks(books);
  }

  public BookResponseDTO getBookWithComments(Long id) throws EntityNotFoundException {
    Optional<Book> bookOpt = bookRepository.findBookWithComments(id);
    if(bookOpt.isPresent()) {
      return bookDTOBuilder.buildBook(bookOpt.get());
    }
    throw new EntityNotFoundException(String.format(BOOK_NOT_FOUND, id));
  }

  private int getChunkSize(int totalBooks) {
    return (totalBooks + pageSizeBook - 1) / pageSizeBook;
  }

  @Transactional
  public void removeBookById(Long id) throws EntityNotFoundException {
    Optional<Book> book = bookRepository.findById(id);
    if(book.isPresent()) {
      commentService.deleteAllByBookId(id);
      bookRepository.deleteById(id);
    } else {
      throw new EntityNotFoundException(String.format(BOOK_NOT_FOUND, id));
    }
  }

  @Transactional
  public void edit(Long id, BookUpdateRequestDTO bookDTO) throws EntityNotFoundException {
    Optional<Book> bookOptional = bookRepository.findById(id);
    if(bookOptional.isPresent()) {
      Book book = bookOptional.get();
      book.update(bookDTO);
    } else {
      throw new EntityNotFoundException(String.format(BOOK_NOT_FOUND, id));
    }
  }

}
