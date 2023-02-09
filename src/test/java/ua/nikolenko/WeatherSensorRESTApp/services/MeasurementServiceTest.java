package ua.nikolenko.WeatherSensorRESTApp.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.nikolenko.WeatherSensorRESTApp.models.Measurement;
import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;
import ua.nikolenko.WeatherSensorRESTApp.repositories.MeasurementRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MeasurementServiceTest {

    @Mock
    private MeasurementRepository measurementRepository;
    private MeasurementService measurementService;

    @BeforeEach
    void setUp() {
        measurementService = new MeasurementService(measurementRepository);
        Scanner scanner = new Scanner("First");
        Measurement measurement = new Measurement(24.5, true);
        measurement.setScanner(scanner);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void canFindAllMeasurements() {
        measurementService.findAll();
        verify(measurementRepository).findAll();
    }

    @Test
    void canAddMeasure() {
        Scanner scanner = new Scanner("First");
        Measurement measurement = new Measurement(24.5, true);
        measurement.setScanner(scanner);
        measurementService.enrichMeasure(measurement);
        measurementService.addMeasure(measurement);
        ArgumentCaptor<Measurement> captor = ArgumentCaptor.forClass(Measurement.class);
        verify(measurementRepository).save(captor.capture());

        Measurement captured = captor.getValue();

        assertEquals(measurement, captured);
        assertNotNull(captured.getScannedIn());
    }


    @Test
    void findRain() {
        measurementService.findRain();
        verify(measurementRepository).countAllByRainingTrue();
    }
}