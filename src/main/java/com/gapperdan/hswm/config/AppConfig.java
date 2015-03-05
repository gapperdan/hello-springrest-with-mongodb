package com.gapperdan.hswm.config;

import com.gapperdan.hswm.service.CountryServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories({"com.gapperdan.hswm.repository"})
@ComponentScan(basePackages = "com.gapperdan.hswm")
public class AppConfig {

    @Bean
    public CountryServiceImpl getCountryService() {
        return new CountryServiceImpl();
    }

}