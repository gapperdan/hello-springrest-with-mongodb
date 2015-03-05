package com.gapperdan.hswm.controller;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.exception.CountryNotFoundException;
import com.gapperdan.hswm.service.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
public class CountryController {

    @Autowired
    CountryServiceImpl countryService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<Country> getAll() {
        return countryService.getAll();
    }

    @RequestMapping(value = "/country/code/{code}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Country getCountryByCode(@PathVariable String code) throws CountryNotFoundException {

        Country country = countryService.getByCode(code);
        if (country != null) {
            return country;
        } else {
            throw new CountryNotFoundException(code);
        }
    }

    @RequestMapping(value = "/country/name/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Country getCountrByNamey(@PathVariable String name) throws CountryNotFoundException {

        Country country = countryService.getByName(name);
        if (country != null) {
            return country;
        } else {
            throw new CountryNotFoundException(name);
        }
    }
}