package com.tms.news.core.models.dtos;

import java.util.UUID;

public record CommentUpdateDto(UUID id, String content) {
}
