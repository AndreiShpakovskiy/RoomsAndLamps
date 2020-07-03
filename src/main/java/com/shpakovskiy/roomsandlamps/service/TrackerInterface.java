package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.entity.Country;

public interface TrackerInterface {

    Country getCountryByIp(String ipAddress);
}
