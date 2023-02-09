package ua.nikolenko.WeatherSensorRESTApp.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.nikolenko.WeatherSensorRESTApp.models.Measurement;
import ua.nikolenko.WeatherSensorRESTApp.services.ScannerService;

@Component
@RequiredArgsConstructor
public class MeasurementValidator implements Validator {

    private final ScannerService scannerService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurement.getScanner() == null) {
            return;
        }
        if (scannerService.findByName(measurement.getScanner().getScannerName()).isEmpty()) {
            errors.rejectValue("scanner", "Scanner with this name not registered");
        }
    }
}
