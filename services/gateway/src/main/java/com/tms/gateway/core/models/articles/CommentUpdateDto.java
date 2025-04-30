package com.tms.gateway.core.models.articles;

import java.util.UUID;

public record CommentUpdateDto(UUID id, String content) {
}
