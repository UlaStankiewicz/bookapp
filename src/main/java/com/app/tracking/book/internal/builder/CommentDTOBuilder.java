package com.app.tracking.book.internal.builder;

import com.app.tracking.book.boundary.dto.CommentResponseDTO;
import com.app.tracking.book.internal.entity.Comment;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CommentDTOBuilder {

  Set<CommentResponseDTO> findFiveNewestComments(Set<Comment> comments) {
    return comments.stream()
        .limit(5)
        .map(comment -> new CommentResponseDTO(comment.getDescription(), comment.getCreationDateTime()))
        .sorted(Comparator.comparing(CommentResponseDTO::getCreationDateTime).reversed())
        .collect(Collectors.toCollection(LinkedHashSet::new));
  }

}
