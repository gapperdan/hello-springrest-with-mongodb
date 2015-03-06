package com.gapperdan.hswm.view;

import com.gapperdan.hswm.domain.Country;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ViewCountryResourceTest {

    public Country country;
    public ViewCountryResource viewCountryResource;

    @Before
    public void setup() {
        country = new Country();
        country.setName("FOO");
        country.setCapital("BAR");
        country.setCode("FF");
        country.setPopulation(1);
    }

    @Test
    public void shouldCreateAViewCountryResource_whenCallingConstructorWithCountry() {
        viewCountryResource = new ViewCountryResource(country);
        assertThat(country.getName(), equalTo(viewCountryResource.getName()));
        assertThat(country.getCapital(), equalTo(viewCountryResource.getCapital()));
        assertThat(country.getPopulation(), equalTo(viewCountryResource.getPopulation()));
    }

}