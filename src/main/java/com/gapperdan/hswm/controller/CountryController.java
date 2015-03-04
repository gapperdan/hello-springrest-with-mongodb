package com.gapperdan.hswm.controller;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.exception.CountryNotFoundException;
import com.gapperdan.hswm.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class CountryController {

    @Autowired
    CountryService countryService;

    @RequestMapping(value = "/country", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody long getCount() {
        return countryService.count();
    }

    @RequestMapping(value = "/country/{name}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Country getCountry(@PathVariable String name) throws CountryNotFoundException {

        Country country = countryService.get(name);
        if (country != null) {
            return country;
        } else {
            throw new CountryNotFoundException(name);
        }
    }
}