package com.sky.nebula.carDealership.functional.config;

import com.sky.nebula.carDealership.CarDealershipApplication;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalServiceConfig {

    private ConfigurableApplicationContext configurableApplicationContext;

    @PostConstruct
    public void startUp(){
        configurableApplicationContext = SpringApplication.run(CarDealershipApplication.class);
    }

    @PreDestroy
    public void shutDown() {
        configurableApplicationContext.close();
    }
}

