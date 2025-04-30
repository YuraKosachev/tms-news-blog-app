package com.tms.dataprovider.core.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.dataprovider.core.constants.DateTimeConstants;

import java.time.LocalDateTime;

public record ProcessedDataDto(
        String title,
        String content,
        String description,
        String category,
        @JsonProperty("sent_at")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
        LocalDateTime sentAt) {
}
