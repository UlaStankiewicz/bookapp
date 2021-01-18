package com.app.tracking.book.boundary.dto;

import com.app.tracking.book.internal.validator.ISBNConstraint;
import com.app.tracking.book.internal.validator.RequestType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class BookUpdateRequestDTO {

  private String title;

  private String author;

  @ISBNConstraint(requestType = RequestType.UPDATE)
  @JsonProperty("isbn")
  private String ISBN;

  @Min(value = 1, message = "value must be equal or bigger than 1.")
  private Integer numberPages;

  @Min(1)
  @Max(5)
  private Integer rating;

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
}
