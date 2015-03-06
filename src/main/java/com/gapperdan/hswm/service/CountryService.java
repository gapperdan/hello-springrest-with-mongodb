package com.gapperdan.hswm.service;

import com.gapperdan.hswm.domain.Country;

import java.util.List;

public interface CountryService {

    public Country getByName(String name);

    public Country getByCode(String code);

    public List<Country> getAll();

    public Long count();

    public Country create(Country country);

    public Country update(Country country);

    public void delete(Country country);
}
