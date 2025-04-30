package com.tms.news.core.models.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tms.news.core.constants.DateTimeConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstants.DATE_TIME_FORMAT)
    private LocalDateTime dateTime;
    private String message;
    private String description;
}