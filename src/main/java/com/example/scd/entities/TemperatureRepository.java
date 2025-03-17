package com.example.scd.entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Integer> {
    @Query("SELECT t.timestamp FROM TemperatureEntity t " +
            " WHERE t.city.id = :cityId ORDER BY t.timestamp DESC")
    List<LocalDateTime> findLastTimestampsByCityId(@Param("cityId") Integer cityId);

    @Query("SELECT t FROM TemperatureEntity t WHERE " +
            "(:lat IS NULL OR t.city.lat = :lat) AND " +
            "(:lon IS NULL OR t.city.lon = :lon) AND " +
            "(:from IS NULL OR t.timestamp >= :from) AND " +
            "(:until IS NULL OR t.timestamp <= :until)")

    List<TemperatureEntity> findTemperatures(@Param("lat") Double lat,
                                             @Param("lon") Double lon,
                                             @Param("from") Timestamp from,
                                             @Param("until") Timestamp until);

    @Query("SELECT t FROM TemperatureEntity t WHERE t.city.id = :cityId AND " +
            "(:from IS NULL OR t.timestamp >= :from) AND " +
            "(:until IS NULL OR t.timestamp <= :until)")
    List<TemperatureEntity> findTemperaturesByCityAndDateRange(@Param("cityId") Integer cityId,
                                                               @Param("from") Timestamp from,
                                                               @Param("until") Timestamp until);

    @Query("SELECT t FROM TemperatureEntity t WHERE t.city.country.id = :countryId AND " +
            "(:from IS NULL OR t.timestamp >= :from) AND " +
            "(:until IS NULL OR t.timestamp <= :until)")
    List<TemperatureEntity> findTemperaturesByCountryAndDateRange(@Param("countryId") Integer countryId,
                                                                  @Param("from") Timestamp from,
                                                                  @Param("until") Timestamp until);
}
