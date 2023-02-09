package ua.nikolenko.WeatherSensorRESTApp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ScannerDTO {

    @Getter
    @Setter
    @NotEmpty
    @Size(min = 3, max = 30, message = "Scanner name must be between 3 and 30 characters")
    private String scannerName;
}
