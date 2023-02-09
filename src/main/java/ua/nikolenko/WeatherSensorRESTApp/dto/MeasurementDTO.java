package ua.nikolenko.WeatherSensorRESTApp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @Getter
    @Setter
    @Column(name = "temperature")
    @Min(value = -100)
    @Max(value = 100)
    @NotNull
    private double temperature;

    @Getter
    @Setter
    @NotNull
    private boolean raining;

    @Getter
    @Setter
    @NotNull
    private ScannerDTO scanner;

}
