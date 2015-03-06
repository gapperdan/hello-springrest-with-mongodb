package com.gapperdan.hswm.repository;

import com.gapperdan.hswm.domain.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends MongoRepository<Country, Long> {

    Country findByName(String countryName);

    Country findByCode(String countryCode);

    List<Country> findAll();

    Country save(Country country);

    void delete(Country country);

    /*
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
    */
}