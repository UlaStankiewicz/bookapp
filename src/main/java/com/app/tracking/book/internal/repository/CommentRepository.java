package com.app.tracking.book.internal.repository;

import com.app.tracking.book.internal.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Modifying
  @Query("delete from Comment c "
      + "where c.book.id = :bookdId")
  void deleteAllByBookId(@Param("bookdId") Long bookId);
}
