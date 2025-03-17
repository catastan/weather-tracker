package com.example.scd.entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "countries", schema = "weather_db")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", unique = true)
    private String nume;

    @NotNull
    @Column(name = "latitude")
    private Double lat;

    @NotNull
    @Column(name = "longitude")
    private Double lon;

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
}