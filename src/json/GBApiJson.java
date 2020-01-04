package json;

import java.util.List;

import com.example.jackson.Item;

public class GBApiJson {

    public String kind;

    public Integer totalItems;

    public List<Item> items = null;

    public String getKind(){
        return kind;
    }

    public Integer getTotalItems(){
        return totalItems;
    }

    public List<Item> getItems(){
        return items;
    }
}
