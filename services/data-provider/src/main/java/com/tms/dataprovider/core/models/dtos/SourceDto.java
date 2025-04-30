package com.tms.dataprovider.core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.dataprovider.core.enums.ProcessingStatus;

import java.util.UUID;

public record SourceDto(UUID id,
                        String link,
                        @JsonProperty("status_code")
                        int statusCode,
                        ProcessedDataDto processedDataDto,
                        ProcessingStatus status) {
}
