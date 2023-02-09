package ua.nikolenko.WeatherSensorRESTApp.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.nikolenko.WeatherSensorRESTApp.models.Measurement;
import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class MeasurementRepositoryTest {

    @Autowired
    private MeasurementRepository measurementRepository;
    @Autowired
    private ScannerRepository scannerRepository;

    @BeforeEach
    void setUp() {
        Scanner scanner = new Scanner("First");
        scannerRepository.save(scanner);
        Measurement measurement1 = new Measurement();
        measurement1.setTemperature(23.5);
        measurement1.setRaining(true);
        measurement1.setScannedIn(LocalDateTime.now());
        measurement1.setScanner(scanner);
        measurementRepository.save(measurement1);
        Measurement measurement2 = new Measurement();
        measurement2.setTemperature(12.5);
        measurement2.setRaining(true);
        measurement2.setScannedIn(LocalDateTime.now());
        measurement2.setScanner(scanner);
        measurementRepository.save(measurement2);
        Measurement measurement3 = new Measurement();
        measurement3.setTemperature(7.7);
        measurement3.setRaining(false);
        measurement3.setScannedIn(LocalDateTime.now());
        measurement3.setScanner(scanner);
        measurementRepository.save(measurement3);
    }

    @AfterEach
    void tearDown() {
        measurementRepository.deleteAll();
    }

    @Test
    void countAllByRainingTrue() {
        int actual = measurementRepository.countAllByRainingTrue();
        assertEquals(2, actual);
    }
}