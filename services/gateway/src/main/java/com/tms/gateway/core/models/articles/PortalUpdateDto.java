package com.tms.gateway.core.models.articles;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PortalUpdateDto(
        @NotNull(message = "id is required")
        UUID id,
        @NotNull(message = "title is required")
        String name,
        String description,
        String url) {
}
