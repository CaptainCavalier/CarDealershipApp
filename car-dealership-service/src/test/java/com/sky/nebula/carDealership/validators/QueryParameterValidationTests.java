package com.sky.nebula.carDealership.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueryParameterValidationTests {
    RequestParameterValidation requestParameterValidation = new RequestParameterValidation();

    @Test
    void validQueryParameterReturnsTrueTest() {
        String validQueryParameter = "bentley";

        boolean response = requestParameterValidation.validateString(validQueryParameter);
        Assertions.assertTrue(response);

    }
}
