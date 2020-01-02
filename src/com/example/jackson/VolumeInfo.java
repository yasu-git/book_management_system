
package com.example.jackson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "authors",
    "publisher",
    "publishedDate",
    "description",
    "industryIdentifiers",
    "readingModes",
    "pageCount",
    "printType",
    "categories",
    "maturityRating",
    "allowAnonLogging",
    "contentVersion",
    "panelizationSummary",
    "imageLinks",
    "language",
    "previewLink",
    "infoLink",
    "canonicalVolumeLink"
})
public class VolumeInfo {

    @JsonProperty("title")
    public String title;
    @JsonProperty("authors")
    public List<String> authors = null;
    @JsonProperty("publisher")
    public String publisher;
    @JsonProperty("publishedDate")
    public String publishedDate;
    @JsonProperty("description")
    public String description;
    @JsonProperty("industryIdentifiers")
    public List<IndustryIdentifier> industryIdentifiers = null;
    @JsonProperty("readingModes")
    public ReadingModes readingModes;
    @JsonProperty("pageCount")
    public Integer pageCount;
    @JsonProperty("printType")
    public String printType;
    @JsonProperty("categories")
    public List<String> categories = null;
    @JsonProperty("maturityRating")
    public String maturityRating;
    @JsonProperty("allowAnonLogging")
    public Boolean allowAnonLogging;
    @JsonProperty("contentVersion")
    public String contentVersion;
    @JsonProperty("panelizationSummary")
    public PanelizationSummary panelizationSummary;
    @JsonProperty("imageLinks")
    public ImageLinks imageLinks;
    @JsonProperty("language")
    public String language;
    @JsonProperty("previewLink")
    public String previewLink;
    @JsonProperty("infoLink")
    public String infoLink;
    @JsonProperty("canonicalVolumeLink")
    public String canonicalVolumeLink;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
