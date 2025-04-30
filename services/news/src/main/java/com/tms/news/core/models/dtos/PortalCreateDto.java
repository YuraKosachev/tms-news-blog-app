package com.tms.news.core.models.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record PortalCreateDto(
        @NotNull(message = "title is required")
        String name,
        String description,
        String url) {
}
