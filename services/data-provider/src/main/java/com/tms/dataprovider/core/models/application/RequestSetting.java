package com.tms.dataprovider.core.models.application;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestSetting {
    private String body;
    private String endpoint;
    private List<String> headers;
}
