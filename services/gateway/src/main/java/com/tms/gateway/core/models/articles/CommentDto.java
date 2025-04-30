package com.tms.gateway.core.models.articles;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.gateway.core.constants.DateTimeConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDto(UUID id,
                         String author,
                         UUID authorId,
                         String content,
                         @JsonProperty("created_at")
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
                         LocalDateTime createdAt) {
}
