package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.Country;
import com.shpakovskiy.roomsandlamps.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryService implements CountryServiceInterface {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.getAllCountries();
    }

    @Override
    public Map<Integer, String> getAllCountriesAlphabetically() {
        return countryRepository.getAllCountriesAlphabetically();
    }

    @Override
    public void addCountry(Country country) {
        countryRepository.addCountry(country);
    }

    @Override
    public void addCountries(List<Country> countries) {
        countryRepository.addCountries(countries);
    }

    @Override
    public Country getCountryByName(String countryName) {
        return countryRepository.getCountryByName(countryName);
    }

    @Override
    public Country getCountryById(int id) {
        return countryRepository.getCountryById(id);
    }

    @Override
    public List<String> getAllCountryNames() {
        return countryRepository.getAllCountryNames();
    }
}
