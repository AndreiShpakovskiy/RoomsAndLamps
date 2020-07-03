package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.Country;
import com.shpakovskiy.roomsandlamps.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountryService implements CountryServiceInterface {

    @Override
    @SuppressWarnings("unchecked") //Used to avoid warnings, caused by unchecked cast
    public List<Country> getAllCountries() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return (List<Country>) session
                    .createQuery("FROM Country")
                    .list();
        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public Map<Integer, String> getAllCountriesAlphabetically() {
        List<Country> allCountries = getAllCountries();

        allCountries.sort((a, b) -> {
            int aLength = a.getName().length();
            int bLength = b.getName().length();
            for (int i = 0; i < Math.min(aLength, bLength); i++) {
                int aChar = a.getName().charAt(i);
                int bChar = b.getName().charAt(i);

                if (aChar != bChar) {
                    return aChar - bChar;
                }
            }

            if (aLength != bLength) {
                return aLength - bLength;
            } else {
                return 0;
            }
        });

        Map<Integer, String> countries = new HashMap<>();
        int id = 1;
        for (Country country : allCountries) {
            countries.put(id, country.getName());
            id++;
        }

        return countries;
    }

    @Override
    public void addCountry(Country country) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(country);
            session.getTransaction().commit();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void addCountries(List<Country> countries) {
        for (Country country : countries) {
            addCountry(country);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Country getCountryByName(String countryName) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            List<Country> countries = (List<Country>) session.createQuery("FROM Country c WHERE c.name = :countryName")
                    .setParameter("countryName", countryName)
                    .list();
            if (!countries.isEmpty()) { return countries.get(0); };
        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public Country getCountryById(int id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Country.class, id);
        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getAllCountryNames() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return (List<String>) session
                    .createQuery("SELECT c.name FROM Country c ORDER BY c.name ASC")
                    .list();
        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
