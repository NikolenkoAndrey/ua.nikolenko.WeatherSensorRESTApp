package ua.nikolenko.WeatherSensorRESTApp.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScannerRepositoryTest {

    @Autowired
    private ScannerRepository scannerRepository;

    @BeforeEach
    void setUp() {
        scannerRepository.save(new Scanner("First"));
        scannerRepository.save(new Scanner("Second"));
        scannerRepository.save(new Scanner("Third"));
    }

    @AfterEach
    void tearDown() {
        scannerRepository.deleteAll();
    }

    @Test
    void findByNameIfScannerIsPresent() {
        String scannerName = "First";

        Optional<Scanner> actual = scannerRepository.findByScannerName(scannerName);

        assertTrue(actual.isPresent());
        assertEquals(scannerName,actual.get().getScannerName());
    }

    @Test
    void findByNameIfScannerIsWrong() {

        Optional<Scanner> actual = scannerRepository.findByScannerName("Wrong");

        assertFalse(actual.isPresent());
    }
}