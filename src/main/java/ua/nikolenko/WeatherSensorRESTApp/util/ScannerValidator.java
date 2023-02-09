package ua.nikolenko.WeatherSensorRESTApp.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.nikolenko.WeatherSensorRESTApp.dto.ScannerDTO;
import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;
import ua.nikolenko.WeatherSensorRESTApp.services.ScannerService;

@Component
@RequiredArgsConstructor
public class ScannerValidator implements Validator {

    private final ScannerService scannerService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ScannerDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Scanner scanner = (Scanner) target;

        if (scannerService.findByName(scanner.getScannerName()).isPresent()) {
            errors.rejectValue("scannerName", "", "This Scanner is already registered!");
        }
    }
}
