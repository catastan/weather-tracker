package com.example.scd.entities;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "cities", schema = "weather_db")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String nume;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lon;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private CountryEntity country;

    @Column(name = "country_id")
    @NotNull
    private Integer idTara;

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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public Integer getIdTara() {
        return idTara;
    }

    public void setIdTara(Integer idTara) {
        this.idTara = idTara;
    }
}