package com.tms.gateway.core.models.articles;

import java.util.UUID;

public record PortalDto(UUID id, String name, String description, String url) {
}
