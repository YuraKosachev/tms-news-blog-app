package com.tms.dataprovider.core.models.parsers.cnn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HasPart {
    @JsonProperty("@type")
    String type;

    @JsonProperty("isAccessibleForFree")
    boolean isAccessibleForFree;

    @JsonProperty("cssSelector")
    String cssSelector;
}
