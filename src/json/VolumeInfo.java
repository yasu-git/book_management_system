package json;

import java.util.List;

import com.example.jackson.ImageLinks;
import com.example.jackson.IndustryIdentifier;
import com.example.jackson.PanelizationSummary;
import com.example.jackson.ReadingModes;

public class VolumeInfo {

    public String title;

    public List<String> authors = null;

    public String publisher;

    public String publishedDate;

    public String description;

    public List<IndustryIdentifier> industryIdentifiers = null;

    public ReadingModes readingModes;

    public Integer pageCount;

    public String printType;

    public List<String> categories = null;

    public String maturityRating;

    public Boolean allowAnonLogging;

    public String contentVersion;

    public PanelizationSummary panelizationSummary;

    public ImageLinks imageLinks;

    public String language;

    public String previewLink;

    public String infoLink;

    public String canonicalVolumeLink;

    public String getTitle(){
        return title;
    }

    public List<String> getAuthors(){
        return authors;
    }

    public String getPublisher(){
        return publisher;
    }

    public String getPublishedDate(){
        return publishedDate;
    }

    public String getTDescription(){
        return description;
    }

    public List<IndustryIdentifier> getIndustryIdentifiers(){
        return industryIdentifiers;
    }

    public ReadingModes getReadingModes(){
        return readingModes;
    }

    public Integer getPageCount(){
        return pageCount;
    }

    public String getPrintType(){
        return printType;
    }

    public List<String> getCategories(){
        return categories;
    }

    public String getMaturityRating(){
        return maturityRating;
    }

    public Boolean getAllowAnonLogging(){
        return allowAnonLogging;
    }

    public String getContentVersion(){
        return contentVersion;
    }

    public PanelizationSummary getPanelizationSummary(){
        return panelizationSummary;
    }

    public ImageLinks getImageLinks(){
        return imageLinks;
    }

    public String getLanguage(){
        return language;
    }

    public String getPreviewLink(){
        return previewLink;
    }

    public String getInfoLink(){
        return infoLink;
    }

    public String getCanonicalVolumeLink(){
        return canonicalVolumeLink;
    }
}
