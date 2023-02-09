package ua.nikolenko.WeatherSensorRESTApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;
import ua.nikolenko.WeatherSensorRESTApp.repositories.ScannerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ScannerServiceTest {

    @Mock
    private ScannerRepository scannerRepository;
    private ScannerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ScannerService(scannerRepository);
    }

    @Test
    void canGetAllScanners() {
        underTest.findAll();
        verify(scannerRepository).findAll();
    }

    @Test
    void canAddNewScanner() {
        Scanner scanner = new Scanner("First");
        underTest.enrichScanner(scanner);
        underTest.addNew(scanner);

        ArgumentCaptor<Scanner> scannerArgumentCaptor = ArgumentCaptor.forClass(Scanner.class);
        verify(scannerRepository).save(scannerArgumentCaptor.capture());

        Scanner captured = scannerArgumentCaptor.getValue();

        assertNotNull(scanner.getRegisteredAt());
        assertEquals(scanner, captured);
    }

    @Test
    void findByCorrectName() {
        Scanner expected = new Scanner("First");
        scannerRepository.save(expected);

        underTest.findByName("First");

        ArgumentCaptor<String > scannerArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(scannerRepository).findByScannerName(scannerArgumentCaptor.capture());

        String captured = scannerArgumentCaptor.getValue();

        assertEquals(expected.getScannerName(),captured);
    }
}