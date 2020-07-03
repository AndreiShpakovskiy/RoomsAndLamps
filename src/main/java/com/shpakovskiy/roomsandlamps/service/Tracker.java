package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.Country;
import org.springframework.stereotype.Service;

@Service
public class Tracker implements TrackerInterface {

    private final CountryService countryService;
    private final IpDataService ipDataService;

    public Tracker(CountryService countryService, IpDataService ipDataService) {
        this.countryService = countryService;
        this.ipDataService = ipDataService;
    }

    @Override
    public Country getCountryByIp(String ipAddress) {
        int countryId = ipDataService.getCountryIdByNumericIp(getNumericIp(ipAddress));
        return countryService.getCountryById(countryId);
    }

    private long getNumericIp(String ipAddress) {
        String[] ipParts = ipAddress.split("\\.");
        long numericIp = 0;
        for (int i = 0; i < ipParts.length; i++) {
            long currentPart = Long.parseLong(ipParts[i]);
            numericIp += (currentPart << ((ipParts.length - i - 1) * 8));
        }
        return numericIp;
    }
}
