package com.app.tracking.book.boundary.dto;

import javax.validation.constraints.NotEmpty;

public class CommentRequestDTO {

  @NotEmpty
  private String description;

  public CommentRequestDTO() {
  }

  public CommentRequestDTO(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
