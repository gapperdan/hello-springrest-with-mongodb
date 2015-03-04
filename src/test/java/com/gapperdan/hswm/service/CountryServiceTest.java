package com.gapperdan.hswm.service;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.exception.CountryNotFoundException;
import com.gapperdan.hswm.repository.CountryRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryServiceTest {

    private CountryRepository countryRepository;
    private Country fooCountry = new Country();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        countryRepository = mock(CountryRepository.class);
        fooCountry = new Country();
        fooCountry.setName("FOO");
        fooCountry.setCapital("BAR");
        fooCountry.setCode("FF");
        fooCountry.setPopulation(0);
    }

    @Test
    public void shouldReturnCountry_whenCountryNameIsFound() {
        when(countryRepository.get("FOO")).thenReturn(fooCountry);
        assertThat("BAR", equalTo(fooCountry.getCapital()));
        assertThat("FF", equalTo(fooCountry.getCode()));
        assertThat(Long.valueOf(0), equalTo(fooCountry.getPopulation()));
    }

    @Test
    public void shouldThrowCountryNotFoundException_whenCountryNameIsNotFound() {
        when(countryRepository.get("BAZ")).thenThrow(CountryNotFoundException.class);
        expectedException.expect(CountryNotFoundException.class);
        countryRepository.get("BAZ");
    }

}