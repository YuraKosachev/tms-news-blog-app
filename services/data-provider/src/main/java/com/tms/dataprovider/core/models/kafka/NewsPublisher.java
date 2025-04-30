package com.tms.dataprovider.core.models.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record NewsPublisher(
    String title,
    String link,
    String content,
    String description,
    String category,
    String authors,
    String portal,
    String mainUrl,
    LocalDateTime sentAt,
    LocalDateTime publishedAt)
{
}
