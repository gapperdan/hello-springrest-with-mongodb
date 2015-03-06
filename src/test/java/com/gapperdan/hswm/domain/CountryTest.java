package com.gapperdan.hswm.domain;

import com.gapperdan.hswm.view.UpdateCountryResource;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CountryTest {

    private Country country;
    private UpdateCountryResource updateCountryResource;

    @Before
    public void setup() {
        country = new Country();
        country.setId("1");
        country.setName("FOO");
        country.setCapital("BAR");
        country.setCode("FF");
        country.setPopulation(0);

        updateCountryResource = new UpdateCountryResource();
        updateCountryResource.setCapital("QUX");
        updateCountryResource.setCode("QQ");
        updateCountryResource.setPopulation(1);

        country.update(updateCountryResource);
    }

    @Test
    public void shouldUpdateCountryObject_whenUpdateCountryResourceIsProvided() {
        assertThat("QUX", equalTo(country.getCapital()));
        assertThat("QQ", equalTo(country.getCode()));
        assertThat(Long.valueOf(1), equalTo(country.getPopulation()));
    }

    @Test
    public void shouldNotUpdateIdAndName_whenUpdateCountryResourceIsProvided() {
        assertThat("1", equalTo(country.getId()));
        assertThat("FOO", equalTo(country.getName()));
    }
}