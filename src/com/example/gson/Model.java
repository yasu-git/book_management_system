
package com.example.gson;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("totalItems")
    @Expose
    public Integer totalItems;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;

}
