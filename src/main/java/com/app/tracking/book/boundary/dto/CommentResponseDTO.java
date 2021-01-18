package com.app.tracking.book.boundary.dto;

import java.time.LocalDateTime;

public class CommentResponseDTO {

  private String description;

  private LocalDateTime creationDateTime;

  public CommentResponseDTO(String description, LocalDateTime creationDateTime) {
    this.description = description;
    this.creationDateTime = creationDateTime;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getCreationDateTime() {
    return creationDateTime;
  }
}
