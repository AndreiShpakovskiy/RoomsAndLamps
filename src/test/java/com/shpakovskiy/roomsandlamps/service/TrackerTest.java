package com.shpakovskiy.roomsandlamps.service;

import com.shpakovskiy.roomsandlamps.controllers.EntranceController;
import com.shpakovskiy.roomsandlamps.repository.CountryRepository;
import com.shpakovskiy.roomsandlamps.repository.IpDataRepository;
import org.junit.Assert;
import org.junit.Test;

public class TrackerTest {

    @Test
    public void FollowingIpShouldBelongBelarus() { //TODO: I'm not sure it's okay to create such tests
        final String MINSK_DEVICE_IP = "134.17.139.29";
        final String REQUIRED_COUNTRY = "Belarus";

        Tracker tracker = new Tracker(new CountryService(new CountryRepository()), new IpDataService(new IpDataRepository())); //FIXME: Autowiring seems kinda impossible here
        System.out.println(tracker.getCountryByIp(MINSK_DEVICE_IP).getName());
        Assert.assertEquals(tracker.getCountryByIp(MINSK_DEVICE_IP).getName(), REQUIRED_COUNTRY);
    }
}
