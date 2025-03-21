package alcadienoquarkus.dto.request.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenStreetMapServiceResponseSearch {

    @JsonProperty("place_id")
    private Long placeId;

    private String licence;

    private String lat;

    private String lon;

    @JsonProperty("display_name")
    private String displayName;

    public OpenStreetMapServiceResponseSearch(Long placeId, String licence, String lat, String lon,
            String displayName) {
        this.placeId = placeId;
        this.licence = licence;
        this.lat = lat;
        this.lon = lon;
        this.displayName = displayName;

    }
    public OpenStreetMapServiceResponseSearch() {
    }

    public static List<OpenStreetMapServiceResponseSearch> fromString(String response) throws JsonMappingException, JsonProcessingException {
        List<OpenStreetMapServiceResponseSearch> list = new ArrayList<>();

        if (response == null) {
            return list; // Retorna lista vazia se não tiver nada
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenStreetMapServiceResponseSearch convert = mapper.readValue(response, OpenStreetMapServiceResponseSearch.class);
        list.add(convert);
        return list;
        }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
