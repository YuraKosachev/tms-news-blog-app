package com.tms.dataprovider.core.models.parsers.cnn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Publisher {
    @JsonProperty("@type")
    private String type;

    @JsonProperty("name")
    String name;

    @JsonProperty("logo")
    String logo;

    @JsonProperty("foundingDate")
    String foundingDate;

    @JsonProperty("url")
    String url;

    @JsonProperty("sameAs")
    List<String> sameAs;
}
