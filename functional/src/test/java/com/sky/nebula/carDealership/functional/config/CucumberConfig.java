package com.sky.nebula.carDealership.functional.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(
        classes = {ComponentConfig.class, LocalServiceConfig.class},
        initializers = ConfigDataApplicationContextInitializer.class)
@CucumberContextConfiguration
@TestPropertySource("classpath:${env:local}.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // lynn suggested adding to wipe the in memory database before each new test run
public class CucumberConfig {
}
