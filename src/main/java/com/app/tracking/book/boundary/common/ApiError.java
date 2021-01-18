package com.app.tracking.book.boundary.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ApiError {

  private HttpStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;

  private String message;

  public ApiError(HttpStatus status, LocalDateTime timestamp, String message) {
    this.status = status;
    this.timestamp = timestamp;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }
}
