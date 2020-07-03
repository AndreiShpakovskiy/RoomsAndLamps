package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.Country;

import java.util.List;
import java.util.Map;

public interface CountryServiceInterface {
    List<Country> getAllCountries();
    Map<Integer, String> getAllCountriesAlphabetically();
    void addCountry(Country country);
    void addCountries(List<Country> countries);
    Country getCountryByName(String countryName);
    Country getCountryById(int id);
    List<String> getAllCountryNames();
}
