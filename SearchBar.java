import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SearchBar {
    private String searchType;
    private String startDate;
    private String endDate;
    private String startLocation;
    private String destination;
    private String place;

    public SearchBar(String searchType, String startDate, String endDate, 
                     String startLocation, String destination, String place) {
        this.searchType = searchType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startLocation = startLocation;
        this.destination = destination;
        this.place = place;
    }


    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}


