package com.tms.dataprovider.core.models.parsers.cnn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Image {
    @JsonProperty("@type")
    String type;

    @JsonProperty("contentUrl")
    String contentUrl;

    @JsonProperty("caption")
    String caption;

    @JsonProperty("sourceOrganization")
    SourceOrganization sourceOrganization;

    @JsonProperty("width")
    String width;

    @JsonProperty("height")
    String height;

    @JsonProperty("creditText")
    String creditText;

    @JsonProperty("dateCreated")
    LocalDateTime dateCreated;
}
