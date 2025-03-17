package com.example.scd.entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    CountryEntity findByNume(String nume);
    List<CountryEntity> findAll();
}
