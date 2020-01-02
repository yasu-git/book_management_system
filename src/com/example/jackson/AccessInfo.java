
package com.example.jackson;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "country",
    "viewability",
    "embeddable",
    "publicDomain",
    "textToSpeechPermission",
    "epub",
    "pdf",
    "webReaderLink",
    "accessViewStatus",
    "quoteSharingAllowed"
})
public class AccessInfo {

    @JsonProperty("country")
    public String country;
    @JsonProperty("viewability")
    public String viewability;
    @JsonProperty("embeddable")
    public Boolean embeddable;
    @JsonProperty("publicDomain")
    public Boolean publicDomain;
    @JsonProperty("textToSpeechPermission")
    public String textToSpeechPermission;
    @JsonProperty("epub")
    public Epub epub;
    @JsonProperty("pdf")
    public Pdf pdf;
    @JsonProperty("webReaderLink")
    public String webReaderLink;
    @JsonProperty("accessViewStatus")
    public String accessViewStatus;
    @JsonProperty("quoteSharingAllowed")
    public Boolean quoteSharingAllowed;
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
