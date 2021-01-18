package com.app.tracking.book.boundary.dto;

import java.util.Set;

public class BookResponseDTO {

  private Long id;

  private String title;

  private String author;

  private String ISBN;

  private Integer numberPages;

  private Integer rating;

  private Set<CommentResponseDTO> comments;

  public BookResponseDTO() {
  }

  public BookResponseDTO(Long id, String title, String author, String ISBN, Integer numberPages,
      Integer rating) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.ISBN = ISBN;
    this.numberPages = numberPages;
    this.rating = rating;
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

  public Set<CommentResponseDTO> getComments() {
    return comments;
  }

  public void setComments(Set<CommentResponseDTO> comments) {
    this.comments = comments;
  }
}
