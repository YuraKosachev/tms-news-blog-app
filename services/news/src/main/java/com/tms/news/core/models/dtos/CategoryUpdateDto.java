package com.tms.news.core.models.dtos;

import java.util.UUID;

public record CategoryUpdateDto(UUID id, String name, String description) {
}
