package com.gapperdan.hswm.service;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.exception.CountryNotFoundException;
import com.gapperdan.hswm.repository.CountryRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryServiceImplTest {

    private CountryRepository countryRepository;
    private Country country = new Country();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    List<Country> countryList = new ArrayList<Country>();

    @Before
    public void setup() {
        countryRepository = mock(CountryRepository.class);
        country = new Country();
        country.setName("FOO");
        country.setCapital("BAR");
        country.setCode("FF");
        country.setPopulation(0);

        countryList = Arrays.asList(
                mock(Country.class),
                mock(Country.class)
        );
    }

    @Test
    public void shouldReturnCountry_whenCountryNameIsFound() {
        when(countryRepository.findByName("FOO")).thenReturn(country);
        assertThat("BAR", equalTo(country.getCapital()));
        assertThat("FF", equalTo(country.getCode()));
        assertThat(Long.valueOf(0), equalTo(country.getPopulation()));
    }

    @Test
    public void shouldReturnCountry_whenCountryCodeIsFound() {
        when(countryRepository.findByCode("FF")).thenReturn(country);
        assertThat("BAR", equalTo(country.getCapital()));
        assertThat("FF", equalTo(country.getCode()));
        assertThat(Long.valueOf(0), equalTo(country.getPopulation()));
    }


    @Test
    public void shouldThrowCountryNotFoundException_whenCountryNameIsNotFound() {
        when(countryRepository.findByName("BAZ")).thenThrow(CountryNotFoundException.class);
        expectedException.expect(CountryNotFoundException.class);
        countryRepository.findByName("BAZ");
    }

    @Test
    public void shouldReturnAllCountries_whenAllCountriesAreRequested() {
        when(countryRepository.findAll()).thenReturn(countryList);
        assertThat(2, equalTo(countryList.size()));
    }

    @Test
    public void shouldReturnCountOfCountries_whenCountIsRequested() {
        when(countryRepository.count()).thenReturn(Long.valueOf(countryList.size()));
        assertThat(Long.valueOf(2), equalTo(Long.valueOf(countryRepository.count())));
    }
}