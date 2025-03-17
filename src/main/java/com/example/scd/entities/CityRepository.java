package com.example.scd.entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    CityEntity findByNume(String nume);
    List<CityEntity> findAll();
    void delete(CityEntity cityEntity);
    void deleteAll();

    @Query("SELECT c FROM CityEntity c WHERE c.country.id = :idTara")
    List<CityEntity> findByIdTara(@Param("idTara") Integer idTara);

    boolean existsByNumeAndIdTara(String nume, Integer idTara);

}
