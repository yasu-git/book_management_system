package com.example.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPrice {
    @SerializedName("amount")
    @Expose
    public Integer amount;
    @SerializedName("currencyCode")
    @Expose
    public String currencyCode;

}
