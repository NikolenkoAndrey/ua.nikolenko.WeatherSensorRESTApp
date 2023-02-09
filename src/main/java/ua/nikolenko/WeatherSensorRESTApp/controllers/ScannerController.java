package ua.nikolenko.WeatherSensorRESTApp.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nikolenko.WeatherSensorRESTApp.dto.ScannerDTO;
import ua.nikolenko.WeatherSensorRESTApp.models.Scanner;
import ua.nikolenko.WeatherSensorRESTApp.services.ScannerService;
import ua.nikolenko.WeatherSensorRESTApp.util.MeasureAddException;
import ua.nikolenko.WeatherSensorRESTApp.util.MeasureErrorResponse;
import ua.nikolenko.WeatherSensorRESTApp.util.ScannerValidator;
import ua.nikolenko.WeatherSensorRESTApp.util.ErrorUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scanner")
@RequiredArgsConstructor
public class ScannerController {

    private final ScannerValidator scannerValidator;
    private final ModelMapper modelMapper;
    private final ScannerService scannerService;

    @RequestMapping()
    public List<ScannerDTO> showAll() {
        return scannerService.findAll().stream().map(this::convertToScannerDTO).collect(Collectors.toList());
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addScanner(@RequestBody @Valid ScannerDTO scannerDTO, BindingResult bindingResult) {

        Scanner scanner = convertToScanner(scannerDTO);

        scannerValidator.validate(scanner, bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorUtils.returnErrorsToClient(bindingResult);
        }
        scannerService.addNew(scanner);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<MeasureErrorResponse> handleException(MeasureAddException e) {
        MeasureErrorResponse errorResponse = new MeasureErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public Scanner convertToScanner(ScannerDTO scannerDTO) {
        return modelMapper.map(scannerDTO, Scanner.class);
    }

    public ScannerDTO convertToScannerDTO(Scanner scanner) {
        return modelMapper.map(scanner, ScannerDTO.class);
    }
}
