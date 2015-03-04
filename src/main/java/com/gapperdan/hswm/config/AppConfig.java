package com.gapperdan.hswm.config;

import com.gapperdan.hswm.service.CountryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories({"com.gapperdan.hswm.repository"})
@ComponentScan(basePackages = "com.gapperdan.hswm")
public class AppConfig {

    @Bean
    public CountryService getCountryService() {
        return new CountryService();
    }

}