package com.tms.news.core.models.dtos;

import java.util.UUID;

public record PortalDto(UUID id, String name, String description, String url) {
}
