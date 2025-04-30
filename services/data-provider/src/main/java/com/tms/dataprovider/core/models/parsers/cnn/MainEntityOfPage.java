package com.tms.dataprovider.core.models.parsers.cnn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MainEntityOfPage {
    @JsonProperty("@type")
    String type;

    @JsonProperty("@context")
    String context;

    @JsonProperty("url")
    String url;

    @JsonProperty("dateModified")
    LocalDateTime dateModified;

    @JsonProperty("inLanguage")
    String inLanguage;

    @JsonProperty("additionalType")
    String additionalType;

    @JsonProperty("publisher")
    Publisher publisher;

    @JsonProperty("name")
    String name;

    @JsonProperty("headline")
    String headline;

    @JsonProperty("description")
    String description;

    @JsonProperty("datePublished")
    LocalDateTime datePublished;
}
