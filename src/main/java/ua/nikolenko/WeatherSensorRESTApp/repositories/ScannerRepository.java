package ua.nikolenko.WeatherSensorRESTApp.repositories;

import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScannerRepository extends JpaRepository<Scanner, Integer> {

    Optional<Scanner> findByScannerName(String name);
}
