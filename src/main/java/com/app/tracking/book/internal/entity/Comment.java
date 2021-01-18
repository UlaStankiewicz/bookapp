package com.app.tracking.book.internal.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@SequenceGenerator(name = "sq_comment", sequenceName = "sq_comment", allocationSize = 1)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_comment")
  private Long id;

  @Column(nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @Column(name = "creation_datetime")
  @CreationTimestamp
  private LocalDateTime creationDateTime;

  public Comment() {
  }

  public Comment(String description, Book book) {
    this.description = description;
    this.book = book;
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public Book getBook() {
    return book;
  }

  public LocalDateTime getCreationDateTime() {
    return creationDateTime;
  }
}
