package com.sky.nebula.carDealership.functional;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/",
glue="com.sky.nebula.carDealership.functional")
public class RunCucumberTest {
}
