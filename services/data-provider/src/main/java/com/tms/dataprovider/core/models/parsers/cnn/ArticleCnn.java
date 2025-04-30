package com.tms.dataprovider.core.models.parsers.cnn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArticleCnn extends ExtraData{
    @JsonProperty("@type")
    String type;

    @JsonProperty("@context")
    String context;

    @JsonProperty("articleBody")
    String articleBody;

    @JsonProperty("articleSection")
    List<String> articleSection;

    @JsonProperty("author")
    List<Author> author;

    @JsonProperty("dateModified")
    LocalDateTime dateModified;

    @JsonProperty("description")
    String description;

    @JsonProperty("headline")
    String headline;

    @JsonProperty("alternativeHeadline")
    String alternativeHeadline;

    @JsonProperty("image")
    List<Image> image;

    @JsonProperty("thumbnailUrl")
    String thumbnailUrl;

    @JsonProperty("inLanguage")
    String inLanguage;

    @JsonProperty("mainEntityOfPage")
    MainEntityOfPage mainEntityOfPage;

    @JsonProperty("publisher")
    Publisher publisher;

    @JsonProperty("timeRequired")
    String timeRequired;

    @JsonProperty("wordCount")
    int wordCount;

    @JsonProperty("datePublished")
    LocalDateTime datePublished;

    @JsonProperty("isAccessibleForFree")
    boolean isAccessibleForFree;

    @JsonProperty("hasPart")
    HasPart hasPart;
}

