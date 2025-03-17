package com.example.scd.entities;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "temperatures", schema = "weather_db")
public class TemperatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value", nullable = false)
    private Double valoare;
    
    private LocalDateTime timestamp;


    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValoare() {
        return valoare;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public void setCityId(Integer cityId) {
        CityEntity city = new CityEntity();
        city.setId(cityId);
        this.city = city;
    }
}