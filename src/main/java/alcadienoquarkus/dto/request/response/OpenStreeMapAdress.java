package alcadienoquarkus.dto.request.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenStreeMapAdress {
    private String healthcare;
    @JsonProperty("house_number")
    private String houseNumber;
    private String road;
    private String city;
    private String municipality;
    private String county;
    @JsonProperty("state_district")
    private String stateDistrict;
    private String state;
    @JsonProperty("ISO3166-2-lvl4")
    private String iso3166;
    private String region;
    private String postcode;
    private String country;
    @JsonProperty("country_code")
    private String countryCode;

    public String getHealthcare() {
        return healthcare;
    }

    public void setHealthcare(String healthcare) {
        this.healthcare = healthcare;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStateDistrict() {
        return stateDistrict;
    }

    public void setStateDistrict(String stateDistrict) {
        this.stateDistrict = stateDistrict;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIso3166() {
        return iso3166;
    }

    public void setIso3166(String iso3166) {
        this.iso3166 = iso3166;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "OpenStreeMapAdress [healthcare=" + healthcare + ", houseNumber=" + houseNumber + ", road=" + road
                + ", city=" + city + ", municipality=" + municipality + ", county=" + county + ", stateDistrict="
                + stateDistrict + ", state=" + state + ", iso3166=" + iso3166 + ", region=" + region + ", postcode="
                + postcode + ", country=" + country + ", countryCode=" + countryCode + "]";
    }

    

}
