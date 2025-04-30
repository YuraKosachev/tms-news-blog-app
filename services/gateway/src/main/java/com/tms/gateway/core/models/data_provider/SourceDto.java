package com.tms.gateway.core.models.data_provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.gateway.core.enums.ProcessingStatus;


import java.util.UUID;

public record SourceDto(UUID id,
                        String link,
                        @JsonProperty("status_code")
                        int statusCode,
                        ProcessedDataDto processedDataDto,
                        ProcessingStatus status) {
}
