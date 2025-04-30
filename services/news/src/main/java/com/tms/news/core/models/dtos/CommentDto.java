package com.tms.news.core.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.news.core.constants.DateTimeConstants;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDto(UUID id,
                         String author,
                         UUID authorId,
                         String content,
                         @JsonProperty("created_at")
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
                         LocalDateTime createdAt) {
}
