package ua.nikolenko.WeatherSensorRESTApp.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import ua.nikolenko.WeatherSensorRESTApp.dto.ScannerDTO;
import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;
import ua.nikolenko.WeatherSensorRESTApp.services.ScannerService;
import ua.nikolenko.WeatherSensorRESTApp.util.ScannerValidator;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ScannerControllerTest {

    @Mock
    private BindingResult bindingResult;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ScannerValidator scannerValidator;
    @Mock
    private ScannerService scannerService;

    @InjectMocks
    private ScannerController scannerController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showAll() {
    }

    @Test
    void addScanner() {
        ScannerDTO scannerDTO = mock(ScannerDTO.class);
       // Scanner scannerMock = mock(Scanner.class);
        Scanner scanner = scannerController.convertToScanner(scannerDTO);
        //when(scannerController.convertToScanner(scannerDTO)).thenReturn(any(Scanner.class));
    }

    @Test
    void convertToScanner() {
        ScannerDTO scannerDTO = mock(ScannerDTO.class);
        Scanner scanner = scannerController.convertToScanner(scannerDTO);
        verify(modelMapper).map(scannerDTO, Scanner.class);
    }

    @Test
    void convertToScannerDTO() {
        Scanner scanner = mock(Scanner.class);
        ScannerDTO scannerDTO = scannerController.convertToScannerDTO(scanner);
        verify(modelMapper).map(scanner, ScannerDTO.class);
    }
}