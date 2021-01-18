package com.app.tracking.book.boundary.dto;

import com.app.tracking.book.internal.validator.ISBNConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookRequestDTO {

  @NotEmpty
  private String title;

  @NotEmpty
  private String author;

  @ISBNConstraint
  @JsonProperty("isbn")
  private String ISBN;

  @NotNull
  @Min(value = 1, message = "value must be equal or bigger than 1.")
  private Integer numberPages;

  @NotNull
  @Min(1)
  @Max(5)
  private Integer rating;

  private List<CommentResponseDTO> comments;

  public BookRequestDTO() {
  }

  public BookRequestDTO(@NotEmpty String title, @NotEmpty String author, String ISBN,
      @NotNull @Min(value = 1, message = "value must be equal or bigger than 1.") Integer numberPages,
      @NotNull @Min(1) @Max(5) Integer rating) {
    this.title = title;
    this.author = author;
    this.ISBN = ISBN;
    this.numberPages = numberPages;
    this.rating = rating;
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

  public List<CommentResponseDTO> getComments() {
    return comments;
  }

  public void setComments(List<CommentResponseDTO> comments) {
    this.comments = comments;
  }
}
