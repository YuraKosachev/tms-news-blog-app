package com.tms.gateway.core.models.articles;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentCreateDto(
        String authorName,
        UUID authorId,
        @NotNull(message = "Article id is required")
        UUID articleId,
        @NotNull(message = "Comment content is required")
        String content
) {
}
