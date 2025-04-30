package com.tms.dataprovider.core.models.parsers.cnn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author{
    @JsonProperty("@type")
    String type;

    @JsonProperty("name")
    String name;

    @JsonProperty("url")
    String url;
}