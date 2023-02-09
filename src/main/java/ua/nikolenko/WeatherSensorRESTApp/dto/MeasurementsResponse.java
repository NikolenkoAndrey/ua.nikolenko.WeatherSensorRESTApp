package ua.nikolenko.WeatherSensorRESTApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class MeasurementsResponse {

    @Getter
    @Setter
    private List<MeasurementDTO> measurements;
}
