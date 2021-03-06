package com.gapperdan.hswm.controller;

import com.gapperdan.hswm.domain.Country;
import com.gapperdan.hswm.exception.CountryNotFoundException;
import com.gapperdan.hswm.service.CountryServiceImpl;
import com.gapperdan.hswm.view.UpdateCountryResource;
import com.gapperdan.hswm.view.ViewCountryResource;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@EnableWebMvc
@Api(value = "countries", description = "Country CRUD endpoints", position = 2)
public class CountryController {

    @Autowired
    CountryServiceImpl countryService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all countries", notes = "Returns all countries!")
    public @ResponseBody List<ViewCountryResource> getAll() {
        List<Country> countryList = countryService.getAll();
        List<ViewCountryResource> viewCountryResourceList = new ArrayList<>(countryList.size());
        for (Country country : countryList) {
            ViewCountryResource viewCountryResource = new ViewCountryResource(country);
            Link linkByName = linkTo(methodOn(CountryController.class).getCountryByName(country.getName())).withRel("countries").withSelfRel();
            Link linkByCode = linkTo(methodOn(CountryController.class).getCountryByCode(country.getCode())).withRel("countries").withSelfRel();
            viewCountryResource.add(linkByName);
            viewCountryResource.add(linkByCode);
            viewCountryResourceList.add(viewCountryResource);
        }

        return viewCountryResourceList;
    }

    @RequestMapping(value = "/countries/count", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Long count() {
        return countryService.count();
    }

    @RequestMapping(value = "/countries/code/{code}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ViewCountryResource getCountryByCode(@PathVariable String code) throws CountryNotFoundException {
        Country country = countryService.getByCode(code);
        if (country != null) {
            ViewCountryResource viewCountryResource = new ViewCountryResource(country);
            Link linkByName = linkTo(methodOn(CountryController.class).getCountryByName(country.getName())).withRel("countries").withSelfRel();
            Link linkByCode = linkTo(methodOn(CountryController.class).getCountryByCode(country.getCode())).withRel("countries").withSelfRel();
            viewCountryResource.add(linkByName);
            viewCountryResource.add(linkByCode);
            return viewCountryResource;
        } else {
            throw new CountryNotFoundException(code);
        }
    }

    @RequestMapping(value = "/countries/name/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ViewCountryResource getCountryByName(@PathVariable String name) throws CountryNotFoundException {
        Country country = countryService.getByName(name);
        if (country != null) {
            ViewCountryResource viewCountryResource = new ViewCountryResource(country);
            Link linkByName = linkTo(methodOn(CountryController.class).getCountryByName(country.getName())).withRel("countries").withSelfRel();
            Link linkByCode = linkTo(methodOn(CountryController.class).getCountryByCode(country.getCode())).withRel("countries").withSelfRel();
            viewCountryResource.add(linkByName);
            viewCountryResource.add(linkByCode);
            return viewCountryResource;
        } else {
            throw new CountryNotFoundException(name);
        }
    }

    @RequestMapping(value = "/countries", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ViewCountryResource createCountry(@Valid @RequestBody Country country) {
        ViewCountryResource viewCountryResource = null;
        Country newCountry = countryService.create(country);

        if (newCountry != null) {
            viewCountryResource = new ViewCountryResource(country);
            Link linkByName = linkTo(methodOn(CountryController.class).getCountryByName(country.getName())).withRel("countries").withSelfRel();
            Link linkByCode = linkTo(methodOn(CountryController.class).getCountryByCode(country.getCode())).withRel("countries").withSelfRel();
            viewCountryResource.add(linkByName);
            viewCountryResource.add(linkByCode);
        }
        return viewCountryResource;
    }

    @RequestMapping(value = "countries/name/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ViewCountryResource updateCountry(@PathVariable String name, @Valid @RequestBody UpdateCountryResource updateCountryResource) throws CountryNotFoundException {
        ViewCountryResource viewCountryResource = null;
        Country country = countryService.getByName(name);

        if (country != null) {
            country.update(updateCountryResource);
            if (countryService.update(country) != null) {
                viewCountryResource = new ViewCountryResource(country);
                Link linkByName = linkTo(methodOn(CountryController.class).getCountryByName(country.getName())).withRel("countries").withSelfRel();
                Link linkByCode = linkTo(methodOn(CountryController.class).getCountryByCode(country.getCode())).withRel("countries").withSelfRel();
                viewCountryResource.add(linkByName);
                viewCountryResource.add(linkByCode);
            }
            return viewCountryResource;
        } else {
            throw new CountryNotFoundException(name);
        }
    }

    @RequestMapping(value = "countries/name/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody Country deleteCountry(@PathVariable String name) throws CountryNotFoundException {
        Country country = countryService.getByName(name);
        if (country != null) {
            countryService.delete(country);
        } else {
            throw new CountryNotFoundException(name);
        }
        return null;
    }
}