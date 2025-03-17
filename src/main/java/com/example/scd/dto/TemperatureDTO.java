package com.example.scd.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TemperatureDTO {
    private Integer idOras;
    private Double valoare;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime timestamp;

    public void setIdOras(Integer idOras) {
        this.idOras = idOras;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
    }

    public void setDate(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getIdOras() {
        return idOras;
    }

    public Double getValoare() {
        return valoare;
    }
}
