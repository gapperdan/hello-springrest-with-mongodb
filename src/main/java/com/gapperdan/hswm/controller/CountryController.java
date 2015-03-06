package com.gapperdan.hswm.controller;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.exception.CountryNotFoundException;
import com.gapperdan.hswm.service.CountryServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;

@Controller
@EnableWebMvc
@Api(value = "country", description = "Country CRUD endpoints", position = 2)
public class CountryController {

    @Autowired
    CountryServiceImpl countryService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all countries", notes = "Returns all countries!")
    public @ResponseBody List<Country> getAll() {
        return countryService.getAll();
    }

    @RequestMapping(value = "/countries/count", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Long count() {
        return countryService.count();
    }

    @RequestMapping(value = "/country/code/{code}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Country getCountryByCode(@PathVariable String code) throws CountryNotFoundException {
        Country country = countryService.getByCode(code);
        if (country != null) {
            return country;
        } else {
            throw new CountryNotFoundException(code);
        }
    }

    @RequestMapping(value = "/country/name/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Country getCountryByName(@PathVariable String name) throws CountryNotFoundException {
        Country country = countryService.getByName(name);
        if (country != null) {
            return country;
        } else {
            throw new CountryNotFoundException(name);
        }
    }

    @RequestMapping(value = "/country", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Country addCountry(@Valid @RequestBody Country country) {
        Country newCountry = countryService.addCountry(country);
        return newCountry;
    }
}