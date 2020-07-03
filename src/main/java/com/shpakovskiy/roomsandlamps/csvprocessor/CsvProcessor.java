package com.shpakovskiy.roomsandlamps.csvprocessor;

import com.shpakovskiy.roomsandlamps.entity.Country;
import com.shpakovskiy.roomsandlamps.entity.IpDataItem;
import com.shpakovskiy.roomsandlamps.service.CountryService;
import com.shpakovskiy.roomsandlamps.service.IpDataService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Please, take a look at README file, located in package with this class.
 */

public class CsvProcessor {
    private static final int IP_FROM_INDEX = 0;
    private static final int IP_TO_INDEX = 1;
    private static final int REGISTERED_WITH_INDEX = 2;
    private static final int REGISTERED_WHEN_INDEX = 3;
    private static final int COUNTRY_CODE_2_INDEX = 4;
    private static final int COUNTRY_CODE_3_INDEX = 5;
    private static final int COUNTRY_NAME_INDEX = 6;

    private final String CSV_FILE_PATH;

    public CsvProcessor(String csvFilePath) {
        this.CSV_FILE_PATH = csvFilePath;
    }

    public void convertCsvToDb() {
        List<String> csvData = getCsvData(CSV_FILE_PATH);
        CountryService countryService = new CountryService();
        countryService.addCountries(getCountries(csvData));
        IpDataService ipDataService = new IpDataService();
        ipDataService.addItems(getIpData(csvData));
    }

    private List<String> getCsvData(String csvFilePath) {
        try {
            return Files.readAllLines(Paths.get(csvFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Country> getCountries(List<String> csvData) {
        HashSet<Country> countries = new HashSet<>();
        for (String csvLine : csvData) {
            Country country = new Country();
            country.setName(parseLine(csvLine)[COUNTRY_NAME_INDEX]);
            country.setCode(parseLine(csvLine)[COUNTRY_CODE_2_INDEX]);
            countries.add(country);
        }
        return new ArrayList<>(countries);
    }

    private List<IpDataItem> getIpData(List<String> csvData) {
        List<IpDataItem> ipDataItems = new ArrayList<>();
        for (String csvLine : csvData) {
            String[] parsedLineParts = parseLine(csvLine);
            IpDataItem ipDataItem = new IpDataItem();
            ipDataItem.setIpFrom(Long.parseLong(parsedLineParts[IP_FROM_INDEX]));
            ipDataItem.setIpTo(Long.parseLong(parsedLineParts[IP_TO_INDEX]));
            ipDataItem.setCountryId(new CountryService()
                    .getCountryByName(parsedLineParts[COUNTRY_NAME_INDEX]).getId());
            ipDataItems.add(ipDataItem);
        }
        return ipDataItems;
    }

    private String[] parseLine(String csvLine) {
        String[] lineItems = csvLine.split(",");
        String[] parsedItems = new String[lineItems.length];

        for (int i = 0; i < lineItems.length; i++) {
            parsedItems[i] = lineItems[i].substring(lineItems[i].indexOf("\"") + 1, lineItems[i].lastIndexOf("\""));
        }

        return parsedItems;
    }
}
