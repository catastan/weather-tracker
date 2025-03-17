package com.example.scd.dto;

public class CityDTO {
    private String nume;
    private Double lat;
    private Double lon;
    private Integer idTara;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getIdTara() {
        return idTara;
    }

    public void setIdTara(Integer idTara) {
        this.idTara = idTara;
    }
}