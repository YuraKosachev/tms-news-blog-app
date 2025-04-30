package com.tms.gateway.core.models.data_provider;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.gateway.core.constants.DateTimeConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record ProcessedDataDto(
        String title,
        String content,
        String description,
        String category,
        @JsonProperty("sent_at")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime sentAt) {
}
