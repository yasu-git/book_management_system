
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
    "country",
    "saleability",
    "isEbook",
    "listPrice",
    "retailPrice",
    "buyLink",
    "offers"
})
public class SaleInfo {

    @JsonProperty("country")
    public String country;
    @JsonProperty("saleability")
    public String saleability;
    @JsonProperty("isEbook")
    public Boolean isEbook;
    @JsonProperty("listPrice")
    public ListPrice listPrice;
    @JsonProperty("retailPrice")
    public RetailPrice retailPrice;
    @JsonProperty("buyLink")
    public String buyLink;
    @JsonProperty("offers")
    public List<Offer> offers = null;
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
