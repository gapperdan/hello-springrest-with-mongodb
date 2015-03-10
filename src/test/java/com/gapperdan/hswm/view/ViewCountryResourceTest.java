package com.gapperdan.hswm.view;

import com.gapperdan.hswm.domain.Country;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ViewCountryResourceTest {

    private Country country;
    private ViewCountryResource viewCountryResource;

    @Before
    public void setup() {
        country = new Country();
        country.setId("1");
        country.setName("FOO");
        country.setCapital("BAR");
        country.setCode("FF");
        country.setPopulation(0);
    }

    @Test
    public void shouldCreateAViewCountryResource_whenCountryIsProvided() {
        viewCountryResource = new ViewCountryResource(country);
        assertThat(country.getName(), equalTo(viewCountryResource.getName()));
        assertThat(country.getCapital(), equalTo(viewCountryResource.getCapital()));
        assertThat(country.getCode(), equalTo(viewCountryResource.getCode()));
        assertThat(country.getPopulation(), equalTo(viewCountryResource.getPopulation()));
    }
}