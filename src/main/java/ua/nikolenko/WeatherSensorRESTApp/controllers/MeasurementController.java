package ua.nikolenko.WeatherSensorRESTApp.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nikolenko.WeatherSensorRESTApp.dto.MeasurementDTO;
import ua.nikolenko.WeatherSensorRESTApp.dto.MeasurementsResponse;
import ua.nikolenko.WeatherSensorRESTApp.models.Measurement;
import ua.nikolenko.WeatherSensorRESTApp.services.MeasurementService;
import ua.nikolenko.WeatherSensorRESTApp.util.MeasureAddException;
import ua.nikolenko.WeatherSensorRESTApp.util.MeasureErrorResponse;
import ua.nikolenko.WeatherSensorRESTApp.util.MeasurementValidator;
import ua.nikolenko.WeatherSensorRESTApp.util.ErrorUtils;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {

    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;

    @GetMapping()
    public MeasurementsResponse showAll() {
        return new MeasurementsResponse(measurementService.findAll().stream()
                .map(this::convertToMeasurementDTO).collect(Collectors.toList()));

    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasure(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                 BindingResult bindingResult) {

        Measurement measurementToAdd = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrorsToClient(bindingResult);
        }

        measurementService.addMeasure(measurementToAdd);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @GetMapping("/rainingDays")
    public Integer rainingDays() {
        return measurementService.findRain();
//        return Math.toIntExact(measurementService.findAll().stream().filter(Measurement::getRaining).count());
    }

    @ExceptionHandler
    private ResponseEntity<MeasureErrorResponse> handleException(MeasureAddException e) {
        MeasureErrorResponse response = new MeasureErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
