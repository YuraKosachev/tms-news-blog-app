package com.tms.gateway.core.models.articles;

import jakarta.validation.constraints.NotNull;

public record PortalCreateDto(
        @NotNull(message = "title is required")
        String name,
        String description,
        String url) {
}
