package com.tms.gateway.core.models.articles;

import java.util.UUID;

public record CategoryUpdateDto(UUID id, String name, String description) {
}
