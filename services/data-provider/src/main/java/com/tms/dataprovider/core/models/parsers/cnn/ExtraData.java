package com.tms.dataprovider.core.models.parsers.cnn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ExtraData {
    @JsonProperty("itemListElement")
    private List<Object> itemListElement;

    @JsonProperty("video")
    private List<Object> video;

}
