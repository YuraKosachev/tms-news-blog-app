package com.tms.gateway.core.models.data_provider;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.gateway.core.constants.DateTimeConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(UUID id,
                      String source,
                      @JsonProperty("start_at")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
                      LocalDateTime startAt,
                      @JsonProperty("end_at")
                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
                      LocalDateTime endAt,
                      String status,
                      long success,
                      long error) {
}
