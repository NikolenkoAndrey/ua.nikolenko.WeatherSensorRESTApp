package ua.nikolenko.WeatherSensorRESTApp.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nikolenko.WeatherSensorRESTApp.models.Measurement;
import ua.nikolenko.WeatherSensorRESTApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void addMeasure(Measurement measurement) {
        enrichMeasure(measurement);
        measurementRepository.save(measurement);
    }


    void enrichMeasure(Measurement measurement) {
        measurement.setScannedIn(LocalDateTime.now());
    }


    public Integer findRain() {
        return measurementRepository.countAllByRainingTrue();
    }
}
