package com.tms.gateway.core.models.articles;

import jakarta.validation.constraints.NotNull;

public record CategoryCreateDto(
        @NotNull(message = "Category name is required")
        String name,
        String description) {
}
