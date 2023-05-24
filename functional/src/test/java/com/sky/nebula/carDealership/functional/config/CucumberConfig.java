package com.sky.nebula.carDealership.functional.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(
        classes = {ComponentConfig.class, LocalServiceConfig.class},
        initializers = ConfigDataApplicationContextInitializer.class)
@CucumberContextConfiguration
@TestPropertySource("classpath:${env:local}.properties")
public class CucumberConfig {
}
