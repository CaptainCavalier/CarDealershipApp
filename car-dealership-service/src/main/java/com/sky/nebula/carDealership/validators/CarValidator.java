package com.sky.nebula.carDealership.validators;

import br.com.fluentvalidator.AbstractValidator;
import com.sky.nebula.carDealership.model.Car;

import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThanOrEqual;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static java.util.function.Predicate.not;

public class CarValidator extends AbstractValidator<Car> {

    @Override
    public void rules() {

        setPropertyOnContext("car");

        ruleFor(Car::getBrand)
                .must(not(stringEmptyOrNull()))
                    .withMessage("Car brand must not be null")
                    .withFieldName("brand");

        ruleFor(Car::getModel)
                .must(not(stringEmptyOrNull()))
                    .withMessage("Car model must not be null")
                    .withFieldName("model");

        ruleFor(Car::getYear)
                .must(not(nullValue()))
                    .withMessage("Car year must not be null")
                    .withFieldName("year")
                .must(greaterThanOrEqual(1900))
                    .withMessage("Car year must be older than 1900")
                    .withFieldName("year");

        ruleFor(Car::getPrice)
                .must(not(nullValue()))
                    .withMessage("Car price must not be null")
                    .withFieldName("price")
                .must(greaterThanOrEqual(1))
                    .withMessage("Car Price must be greater than 0")
                    .withFieldName("price");

        ruleFor(Car::getMileage)
                .must(not(nullValue()))
                    .withMessage("Car mileage must not be null")
                    .withFieldName("mileage");

        ruleFor(Car::getColour)
                .must(not(stringEmptyOrNull()))
                    .withMessage("Car colour must not be null")
                    .withFieldName("colour");

    }
}
