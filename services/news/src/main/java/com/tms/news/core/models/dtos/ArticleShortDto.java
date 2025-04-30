package com.tms.news.core.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.news.core.constants.DateTimeConstants;

import java.time.LocalDateTime;
import java.util.UUID;

public record ArticleShortDto(
        UUID id,
        String title,
        String description,
        String author,
        String portal,
        String link,
        @JsonProperty("published_at")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
        LocalDateTime publishedAt) {
}
