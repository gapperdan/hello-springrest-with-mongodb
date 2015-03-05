package com.gapperdan.hswm.config;

import com.gapperdan.hswm.service.CountryServiceImpl;
import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories({"com.gapperdan.hswm.repository"})
@ComponentScan(basePackages = "com.gapperdan.hswm")
@EnableSwagger
public class AppConfig {

    @Bean
    public CountryServiceImpl getCountryService() {
        return new CountryServiceImpl();
    }

}