package com.gapperdan.hswm.service;

import com.gapperdan.hswm.domain.Country;

import java.util.List;

public interface CountryService {

    public Country getByName(String name);

    public Country getByCode(String code);

    public List<Country> getAll();

    public Long count();
}
