
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
    "kind",
    "id",
    "etag",
    "selfLink",
    "volumeInfo",
    "saleInfo",
    "accessInfo",
    "searchInfo"
})
public class Item {

    @JsonProperty("kind")
    public String kind;
    @JsonProperty("id")
    public String id;
    @JsonProperty("etag")
    public String etag;
    @JsonProperty("selfLink")
    public String selfLink;
    @JsonProperty("volumeInfo")
    public VolumeInfo volumeInfo;
    @JsonProperty("saleInfo")
    public SaleInfo saleInfo;
    @JsonProperty("accessInfo")
    public AccessInfo accessInfo;
    @JsonProperty("searchInfo")
    public SearchInfo searchInfo;
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
