package com.tms.gateway.core.models.articles;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ArticleUpdateDto(
        UUID id,

        @NotNull(message = "Article title is required")
        String title,

        @NotNull(message = "Article content is required")
        String content,

        @NotNull(message = "Article description is required")
        String description,

        @JsonProperty("author_id")
        UUID authorId,

        @JsonProperty("author_name")
        String authorName
) {
}