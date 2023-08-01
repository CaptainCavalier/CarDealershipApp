package com.sky.nebula.carDealership.validators;

import com.sky.nebula.carDealership.exceptions.InvalidQueryParameterException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class RequestParameterValidation {
    public boolean validateString(String requestVariable) {

        // Check if variable is null/empty
        if (requestVariable == null || requestVariable.trim().isEmpty()) {
            throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
        }

        // Check if variable contains whitespace or special characters
        if (requestVariable.matches(".*\\s+.*") || !requestVariable.matches("^[\\w-]*$")) {
            throw new InvalidQueryParameterException(String.valueOf(Map.of("Description", "Incorrect query parameter provided")), HttpStatus.BAD_REQUEST);
        }

        return true;
    }
}
