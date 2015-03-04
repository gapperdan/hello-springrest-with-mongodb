package com.gapperdan.hswm.repository;

import com.gapperdan.hswm.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${field.countries.name}")
    private String countriesName;

    public long countAllCountries() {
        return mongoTemplate.count(null, Country.class);
    }

    public Country get(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where(countriesName).is(name.toUpperCase()));
        return mongoTemplate.findOne(query, Country.class);
    }
}