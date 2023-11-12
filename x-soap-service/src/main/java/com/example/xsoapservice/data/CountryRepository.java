package com.example.xsoapservice.data;


import at.or.mum.soap.gs_producing_web_service.Country;
import at.or.mum.soap.gs_producing_web_service.Currency;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {

        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setPopulation(46704314);
        spain.setCurrency(Currency.EUR);

        countries.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);

        countries.put(poland.getName(), poland);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        countries.put(uk.getName(), uk);

        Country usa = new Country();
        usa.setName("USA");
        usa.setCapital("Washington");
        usa.setPopulation(120000000);
        usa.setCurrency(Currency.USD);

        countries.put(usa.getName(), usa);
    }

    public Country findByName(String name) {
        return countries.get(name);
    }
}
