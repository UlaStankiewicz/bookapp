package com.app.tracking.book.internal.repository;

import com.app.tracking.book.internal.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  /**
   * Get books by given pageable criteria.
   *
   * @param pageable
   * @return pageable books.
   */
  @Query("select b from Book b")
  List<Book> findBooks(Pageable pageable);

  @Query("select b from Book b " +
      "left join fetch b.comments " +
      "where b.id = :id")
  Optional<Book> findBookWithComments(Long id);
}
