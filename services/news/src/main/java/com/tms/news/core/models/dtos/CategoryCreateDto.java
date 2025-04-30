package com.tms.news.core.models.dtos;

import jakarta.validation.constraints.NotNull;

public record CategoryCreateDto(
        @NotNull(message = "Category name is required")
        String name,
        String description) {
}
