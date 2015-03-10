package com.gapperdan.hswm.view;

import com.gapperdan.hswm.domain.Country;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

public class ViewCountryResource extends ResourceSupport {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String capital;

    @Getter
    @Setter
    private long population;

    public ViewCountryResource(){
    }

    public ViewCountryResource(Country country) {
        this.name = country.getName();
        this.code = country.getCode();
        this.capital = country.getCapital();
        this.population = country.getPopulation();
    }
}
