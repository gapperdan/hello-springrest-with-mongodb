package com.gapperdan.hswm.service;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    //public long count() {
    //    return countryRepository.countAllCountries();
    //}

    public Country getByName(String countryName) {
        return countryRepository.findByName(countryName);
    }

    @Override
    public Country getByCode(String countryCode) {
        return countryRepository.findByCode(countryCode);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Long count() {
        return countryRepository.count();
    }

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Country country) {
        return countryRepository.save(country); }
}