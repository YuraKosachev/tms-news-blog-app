package com.tms.gateway.core.models.articles;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.gateway.core.constants.DateTimeConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ArticleDto(
        UUID id,
        String title,
        String content,
        @JsonProperty("published_at")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
        LocalDateTime publishedAt,
        List<CategoryDto> category){
}
