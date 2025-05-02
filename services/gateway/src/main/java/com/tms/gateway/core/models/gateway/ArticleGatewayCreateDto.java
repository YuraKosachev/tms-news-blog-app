package com.tms.gateway.core.models.gateway;

public record ArticleGatewayCreateDto(
        String title,
        String content,
        String description
) {
}
