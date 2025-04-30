package com.tms.dataprovider.core.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tms.dataprovider.core.constants.DateTimeConstants;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(UUID id,
                      String source,
                      @JsonProperty("start_at")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
                      LocalDateTime startAt,
                      @JsonProperty("end_at")
                      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
                      LocalDateTime endAt,
                      String status,
                      long success,
                      long error) {
}
