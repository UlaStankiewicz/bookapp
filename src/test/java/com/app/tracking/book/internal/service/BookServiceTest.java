package com.app.tracking.book.internal.service;

import com.app.tracking.book.boundary.dto.BookRequestDTO;
import com.app.tracking.book.boundary.dto.BookResponseDTO;
import com.app.tracking.book.internal.builder.BookDTOBuilder;
import com.app.tracking.book.internal.entity.Book;
import com.app.tracking.book.internal.mapper.BookMapper;
import com.app.tracking.book.internal.repository.BookRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @Mock
  private BookDTOBuilder bookDTOBuilder;

  @Mock
  private BookMapper bookMapper;

  @Mock
  private CommentService commentService;

  @InjectMocks
  private BookService testee;

  @Before
  public void setUp() {
    testee = new BookService(bookRepository, bookDTOBuilder, bookMapper, commentService);
  }

  @Test
  void createBook() {
    //given
    BookRequestDTO bookRequestDTO = new BookRequestDTO();
    Book book = new Book(1L);

    //when
    Mockito.when(bookMapper.fromRequestToEntity(bookRequestDTO)).thenReturn(book);
    Mockito.when(bookRepository.save(book)).thenReturn(book);

    Long result = testee.createBook(bookRequestDTO);

    //then
    Assertions.assertEquals(1L, result);
  }

  @Test
  void getAllBooks() {
    //given
    BookResponseDTO book = new BookResponseDTO(1L, "Title", "Author", "123456789-1", 500, 4);

    //when
    Mockito.when(bookRepository.count()).thenReturn(100L);
    Mockito.when(bookDTOBuilder.buildBooks(Mockito.anyList())).thenReturn(Collections.singletonList(book));

    List<BookResponseDTO> result = testee.getAllBooks();

    //then
    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals("Title", result.get(0).getTitle());
  }

  //TODO
  @Test
  void getBookWithComments() {
  }

  //TODO
  @Test
  void removeBookById() {
  }

  //TODO
  @Test
  void edit() {
  }
}