package aladin.rx2retrofit2;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceDTO {

    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = null;
    @SerializedName("index_strings")
    @Expose
    private List<String> indexStrings = null;
    @SerializedName("airport_name")
    @Expose
    private Object airportName;
    @SerializedName("searches_count")
    @Expose
    private Integer searchesCount;

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getIndexStrings() {
        return indexStrings;
    }

    public void setIndexStrings(List<String> indexStrings) {
        this.indexStrings = indexStrings;
    }

    public Object getAirportName() {
        return airportName;
    }

    public void setAirportName(Object airportName) {
        this.airportName = airportName;
    }

    public Integer getSearchesCount() {
        return searchesCount;
    }

    public void setSearchesCount(Integer searchesCount) {
        this.searchesCount = searchesCount;
    }

}