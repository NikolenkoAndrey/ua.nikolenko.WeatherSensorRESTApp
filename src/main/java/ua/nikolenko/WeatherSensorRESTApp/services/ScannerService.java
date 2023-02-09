package ua.nikolenko.WeatherSensorRESTApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;
import ua.nikolenko.WeatherSensorRESTApp.repositories.ScannerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScannerService {
    private final ScannerRepository scannerRepository;

    @Transactional
    public void addNew(Scanner scanner) {
        enrichScanner(scanner);
        scannerRepository.save(scanner);
    }

    public List<Scanner> findAll() {
        return scannerRepository.findAll();
    }

    public Optional<Scanner> findByName(String s) {
        return scannerRepository.findByScannerName(s);
    }


    void enrichScanner(Scanner scanner) {
        scanner.setRegisteredAt(LocalDateTime.now());
    }
}

