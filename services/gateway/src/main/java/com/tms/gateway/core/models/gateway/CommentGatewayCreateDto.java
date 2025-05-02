package com.tms.gateway.core.models.gateway;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record CommentGatewayCreateDto(
        @NotNull(message = "Article id is required")
        UUID articleId,
        @NotNull(message = "Comment content is required")
        String content
) {
}