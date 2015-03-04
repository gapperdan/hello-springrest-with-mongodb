package com.gapperdan.hswm.service;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.exception.CountryNotFoundException;
import com.gapperdan.hswm.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public long count() {
        return countryRepository.countAllCountries();
    }

    public Country get(String name) {
        return countryRepository.get(name);
    }

}