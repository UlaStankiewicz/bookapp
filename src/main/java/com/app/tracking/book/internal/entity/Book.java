package com.app.tracking.book.internal.entity;

import com.app.tracking.book.boundary.dto.BookUpdateRequestDTO;
import com.app.tracking.book.internal.validator.ISBNConstraint;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.BatchSize;

@Entity
@SequenceGenerator(name = "sq_book", sequenceName = "sq_book", allocationSize = 1)
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_book")
  private Long id;

  @Column(nullable = false)
  @NotEmpty
  private String title;

  @Column(nullable = false)
  @NotEmpty
  private String author;

  @ISBNConstraint
  private String ISBN;

  @Column(nullable = false, name = "number_pages")
  @NotNull
  @Min(value = 1)
  private Integer numberPages;

  @Min(1)
  @Max(5)
  @NotNull
  @Column(nullable = false)
  private Integer rating;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
  @BatchSize(size = 1000)
  @OrderBy("creationDateTime DESC")
  private Set<Comment> comments;

  public Book() {
  }

  public Book(Long id) {
    this.id = id;
  }

  public Book(String title, String author, String ISBN, Integer numberPages, Integer rating) {
    this.title = title;
    this.author = author;
    this.ISBN = ISBN;
    this.numberPages = numberPages;
    this.rating = rating;
  }

  public void update(BookUpdateRequestDTO bookDTO) {
    if (bookDTO.getTitle() != null) {
      this.setTitle(bookDTO.getTitle());
    }

    if (bookDTO.getAuthor() != null) {
      this.setAuthor(bookDTO.getAuthor());
    }

    if (bookDTO.getISBN() != null) {
      this.setISBN(bookDTO.getISBN());
    }

    if (bookDTO.getRating() != null) {
      this.setRating(bookDTO.getRating());
    }

    if (bookDTO.getNumberPages() != null) {
      this.setNumberPages(bookDTO.getNumberPages());
    }
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getISBN() {
    return ISBN;
  }

  public Integer getNumberPages() {
    return numberPages;
  }

  public Integer getRating() {
    return rating;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
  }

  public void setNumberPages(Integer numberPages) {
    this.numberPages = numberPages;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }
}
