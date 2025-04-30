package com.tms.news.core.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.news.core.constants.DateTimeConstants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ArticleDescriptionDto(
        UUID id,
        String title,
        String content,
        @JsonProperty("published_at")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
        LocalDateTime publishedAt,
        List<CategoryDto> categories,
        List<CommentDto> comments) {
}
