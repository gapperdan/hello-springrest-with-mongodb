package com.gapperdan.hswm.controller;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.exception.CountryNotFoundException;
import com.gapperdan.hswm.service.CountryServiceImpl;
import com.gapperdan.hswm.view.UpdateCountryResource;
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

public class CountryControllerTest {

    private CountryServiceImpl countryService;
    private Country country;
    private Country updatedCountry;

    List<Country> countryList = new ArrayList<Country>();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        countryService = mock(CountryServiceImpl.class);

        country = new Country();
        country.setId("1");
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
        when(countryService.getByName("FOO")).thenReturn(country);
        assertThat("BAR", equalTo(country.getCapital()));
        assertThat("FF", equalTo(country.getCode()));
        assertThat(Long.valueOf(0), equalTo(country.getPopulation()));
    }

    @Test
    public void shouldReturnCountry_whenCountryCodeIsFound() {
        when(countryService.getByCode("FF")).thenReturn(country);
        assertThat("BAR", equalTo(country.getCapital()));
        assertThat("FF", equalTo(country.getCode()));
        assertThat(Long.valueOf(0), equalTo(country.getPopulation()));
    }

    @Test
    public void shouldThrowCountryNotFoundException_whenCountryNameIsNotFound() {
        when(countryService.getByName("THUD")).thenThrow(CountryNotFoundException.class);
        expectedException.expect(CountryNotFoundException.class);
        countryService.getByName("THUD");
    }

    @Test
    public void shouldReturnAllCountries_WhenAllCountriesAreRequested() {
        when(countryService.getAll()).thenReturn(countryList);
        assertThat(2, equalTo(countryList.size()));
    }

    @Test
    public void shouldReturnCountOfCountries_whenCountIsRequested() {
        when(countryService.count()).thenReturn(Long.valueOf(countryList.size()));
        assertThat(Long.valueOf(2), equalTo(Long.valueOf(countryService.count())));
    }

    @Test
    public void shouldReturnNewCountry_whenAddNewCountryIsSuccessful() {
        when(countryService.create(country)).thenReturn(country);
        assertThat(country, equalTo(countryService.create(country)));
    }

    @Test
    public void shouldReturnUpdatedCountry_whenUpdateCountryIsSuccessful() {
        updatedCountry = country;
        updatedCountry.setCode("QU");
        updatedCountry.setCapital("QUX");
        updatedCountry.setPopulation(1);

        UpdateCountryResource updateCountryResource = new UpdateCountryResource();
        updateCountryResource.setCode("QU");
        updateCountryResource.setCapital("QUX");
        updateCountryResource.setPopulation(1);

        country.update(updateCountryResource);

        when(countryService.update(country)).thenReturn(updatedCountry);
        assertThat(Long.valueOf(1), equalTo(updatedCountry.getPopulation()));
        assertThat("QUX", equalTo(updatedCountry.getCapital()));
        assertThat("FOO", equalTo(updatedCountry.getName()));
        assertThat("1", equalTo(updatedCountry.getId()));
    }
}