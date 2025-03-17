package com.example.scd.dto;
import javax.validation.constraints.NotNull;

public class CountryDTO {
    @NotNull
    private String nume;
    @NotNull
    private Double lat;
    @NotNull
    private Double lon;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
