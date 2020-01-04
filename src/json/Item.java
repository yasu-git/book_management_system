package json;

import com.example.jackson.AccessInfo;
import com.example.jackson.SaleInfo;
import com.example.jackson.SearchInfo;
import com.example.jackson.VolumeInfo;

public class Item {
    public String kind;

    public String id;

    public String etag;

    public String selfLink;

    public VolumeInfo volumeInfo;

    public SaleInfo saleInfo;

    public AccessInfo accessInfo;

    public SearchInfo searchInfo;

    public String getKind(){
        return kind;
    }

    public String getId(){
        return id;
    }
    public String getEtag(){
        return etag;
    }

    public String getSelfLink(){
        return selfLink;
    }

    public VolumeInfo getVolumeInfo(){
        return volumeInfo;
    }

    public SaleInfo getSaleInfo(){
        return saleInfo;
    }

    public AccessInfo getAccessInfo(){
        return accessInfo;
    }

    public SearchInfo getSearchInfo(){
        return searchInfo;
    }

}
