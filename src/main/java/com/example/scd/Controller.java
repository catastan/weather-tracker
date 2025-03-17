package com.example.scd;
import com.example.scd.dto.CityDTO;
import com.example.scd.dto.CountryDTO;
import com.example.scd.dto.TemperatureDTO;
import com.example.scd.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class Controller {
    CountryRepository countryRepository;
    CityRepository cityRepository;
    TemperatureRepository temperatureRepository;

    @Autowired
    public Controller(CountryRepository countryRepository, CityRepository cityRepository, TemperatureRepository temperatureRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.temperatureRepository = temperatureRepository;
    }

    @PostMapping("/api/countries")
    public ResponseEntity<?> addCountry(@Valid @RequestBody CountryDTO countryDTO) {
        try {
            CountryEntity country = new CountryEntity();
            country.setNume(countryDTO.getNume());
            country.setLat(countryDTO.getLat());
            country.setLon(countryDTO.getLon());

            country = countryRepository.save(country);

            return new ResponseEntity<>(Map.of("id", country.getId()), HttpStatus.CREATED);
        } catch (org.hibernate.exception.ConstraintViolationException e) {

            return new ResponseEntity<>("Duplicate entry for country name", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/countries")
    public ResponseEntity<?> getAllCountries() {
        try {
            List<CountryEntity> countries = countryRepository.findAll();
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/countries/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable Integer id, @RequestBody CountryDTO countryDTO) {
        if (id == null) {
            return new ResponseEntity<>("The given id must not be null!", HttpStatus.BAD_REQUEST);
        }
        try {
            CountryEntity country = countryRepository.findById(id).orElse(null);
            if (country == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            country.setNume(countryDTO.getNume());
            country.setLat(countryDTO.getLat());
            country.setLon(countryDTO.getLon());

            country = countryRepository.save(country);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/countries/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer id) {
        try {
            CountryEntity country = countryRepository.findById(id).orElse(null);
            if (country == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            countryRepository.delete(country);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/cities")
    public ResponseEntity<?> addCity(@RequestBody CityDTO cityDTO) {
        try {
            if (cityDTO.getIdTara() == null) {
                return new ResponseEntity<>("Country ID must not be null", HttpStatus.BAD_REQUEST);
            }

            CountryEntity country = countryRepository.findById(cityDTO.getIdTara()).orElse(null);
            if (country == null) {
                return new ResponseEntity<>("Country not found", HttpStatus.NOT_FOUND);
            }

            if (cityRepository.existsByNumeAndIdTara(cityDTO.getNume(), cityDTO.getIdTara())) {
                return new ResponseEntity<>("City already exists in the specified country", HttpStatus.CONFLICT);
            }

            CityEntity city = new CityEntity();
            city.setNume(cityDTO.getNume());
            city.setLat(cityDTO.getLat());
            city.setLon(cityDTO.getLon());
            city.setIdTara(cityDTO.getIdTara());
            city.setCountry(country);

            city = cityRepository.save(city);

            return new ResponseEntity<>(Map.of("id", city.getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/cities")
    public ResponseEntity<?> getAllCities() {
        try {
            List<CityEntity> cities = cityRepository.findAll();
            List<CityDTO> cityDTOs = cities.stream().map(city -> {
                CityDTO dto = new CityDTO();
                dto.setId(city.getId());
                dto.setNume(city.getNume());
                dto.setLat(city.getLat());
                dto.setLon(city.getLon());
                dto.setIdTara(city.getIdTara());
                return dto;
            }).collect(Collectors.toList());
            return new ResponseEntity<>(cityDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/cities/country/{id_Tara}")
    public ResponseEntity<?> getCitiesByCountryId(@PathVariable Integer id_Tara) {
        try {
            List<CityEntity> cities = cityRepository.findByIdTara(id_Tara);
            List<CityDTO> cityDTOs = cities.stream().map(city -> {
                CityDTO dto = new CityDTO();
                dto.setId(city.getId());
                dto.setNume(city.getNume());
                dto.setLat(city.getLat());
                dto.setLon(city.getLon());
                dto.setIdTara(city.getIdTara());
                return dto;
            }).collect(Collectors.toList());
            return new ResponseEntity<>(cityDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/cities/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Integer id, @RequestBody CityDTO cityDTO) {
        if (id == null) {
            return new ResponseEntity<>("The given id must not be null!", HttpStatus.BAD_REQUEST);
        }
        try {
            CityEntity city = cityRepository.findById(id).orElse(null);
            if (city == null) {
                return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
            }

            CountryEntity country = countryRepository.findById(cityDTO.getIdTara()).orElse(null);
            if (country == null) {
                return new ResponseEntity<>("Country not found", HttpStatus.NOT_FOUND);
            }

            city.setNume(cityDTO.getNume());
            city.setLat(cityDTO.getLat());
            city.setLon(cityDTO.getLon());
            city.setIdTara(cityDTO.getIdTara());

            city = cityRepository.save(city);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            return new ResponseEntity<>("Duplicate entry for city name", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/cities/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id) {
        try {
            CityEntity city = cityRepository.findById(id).orElse(null);
            if (city == null) {
                return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
            }
            cityRepository.delete(city);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/temperatures")
    public ResponseEntity<?> addTemperature(@RequestBody TemperatureDTO temperatureDTO) {
        try {
            CityEntity city = cityRepository.findById(temperatureDTO.getIdOras()).orElse(null);
            if (city == null) {
                return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
            }

            if (temperatureDTO.getValoare() == null) {
                return new ResponseEntity<>("Temperature value must not be null", HttpStatus.BAD_REQUEST);
            }

            TemperatureEntity temperature = new TemperatureEntity();
            temperature.setCityId(temperatureDTO.getIdOras());
            temperature.setTimestamp(LocalDateTime.now());
            temperature.setValoare(temperatureDTO.getValoare());
            TemperatureEntity savedTemperature = temperatureRepository.save(temperature);

            return new ResponseEntity<>(Map.of("id", savedTemperature.getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/temperatures")
    public ResponseEntity<?> getTemperatures(
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lon,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String until) {

        LocalDateTime fromDateTime = null;
        LocalDateTime untilDateTime = null;

        try {
            if (from != null) {
                fromDateTime = LocalDateTime.parse(from);
            }
            if (until != null) {
                untilDateTime = LocalDateTime.parse(until);
            }
        } catch (DateTimeParseException e) {
            if (until != null) {
                return new ResponseEntity<>(List.of(), HttpStatus.OK);
            }
            fromDateTime = null;
            untilDateTime = null;
        }

        Timestamp fromTimestamp = fromDateTime != null ? Timestamp.valueOf(fromDateTime) : null;
        Timestamp untilTimestamp = untilDateTime != null ? Timestamp.valueOf(untilDateTime) : null;

        List<TemperatureEntity> temperatures = temperatureRepository.findTemperatures(lat, lon, fromTimestamp, untilTimestamp);

        List<Map<String, Object>> response = temperatures.stream().map(temp -> {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("id", temp.getId());
            tempMap.put("valoare", temp.getValoare());
            tempMap.put("timestamp", temp.getTimestamp());
            return tempMap;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/temperatures/cities/{id_oras}")
    public ResponseEntity<?> getTemperaturesByCity(
            @PathVariable Integer id_oras,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime until) {

        Timestamp fromTimestamp = from != null ? Timestamp.valueOf(from) : null;
        Timestamp untilTimestamp = until != null ? Timestamp.valueOf(until) : null;

        List<TemperatureEntity> temperatures = temperatureRepository.findTemperaturesByCityAndDateRange(id_oras, fromTimestamp, untilTimestamp);

        List<Map<String, Object>> response = temperatures.stream().map(temp -> {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("id", temp.getId());
            tempMap.put("valoare", temp.getValoare());
            tempMap.put("timestamp", temp.getTimestamp());
            return tempMap;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/temperatures/countries/{id_tara}")
    public ResponseEntity<?> getTemperaturesByCountry(
            @PathVariable Integer id_tara,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime until) {

        Timestamp fromTimestamp = from != null ? Timestamp.valueOf(from) : null;
        Timestamp untilTimestamp = until != null ? Timestamp.valueOf(until) : null;

        List<TemperatureEntity> temperatures = temperatureRepository.findTemperaturesByCountryAndDateRange(id_tara, fromTimestamp, untilTimestamp);

        List<Map<String, Object>> response = temperatures.stream().map(temp -> {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("id", temp.getId());
            tempMap.put("valoare", temp.getValoare());
            tempMap.put("timestamp", temp.getTimestamp());
            return tempMap;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/api/temperatures/{id}")
    public ResponseEntity<?> updateTemperature(@PathVariable Integer id, @RequestBody TemperatureDTO request) {
        try {
            Optional<TemperatureEntity> optionalTemperature = temperatureRepository.findById(id);
            if (optionalTemperature.isPresent()) {
                TemperatureEntity temperature = optionalTemperature.get();
                Optional<CityEntity> optionalCity = cityRepository.findById(request.getIdOras());
                if (optionalCity.isPresent()) {
                    temperature.setCity(optionalCity.get());
                    temperature.setValoare(request.getValoare());
                    TemperatureEntity updatedTemperature = temperatureRepository.save(temperature);
                    return ResponseEntity.ok(updatedTemperature);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Temperature not found");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/api/temperatures/{id}")
    public ResponseEntity<?> deleteTemperature(@PathVariable Integer id) {
        try {
            Optional<TemperatureEntity> optionalTemperature = temperatureRepository.findById(id);
            if (optionalTemperature.isPresent()) {
                temperatureRepository.delete(optionalTemperature.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Temperature not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
