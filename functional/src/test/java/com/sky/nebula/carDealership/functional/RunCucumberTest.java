package com.sky.nebula.carDealership.functional;

import com.sky.nebula.carDealership.controllers.CarController;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/",
glue="com.sky.nebula.carDealership.functional")
public class RunCucumberTest {

}
