package ua.nikolenko.WeatherSensorRESTApp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorUtils {
    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorsMsg = new StringBuilder();

        List<FieldError> errorList = bindingResult.getFieldErrors();
        for (FieldError error : errorList) {
            errorsMsg.append(error.getField()).append(" - ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }
        throw new MeasureAddException(errorsMsg.toString());
    }
}
