package com.shpakovskiy.roomsandlamps.security;

import com.shpakovskiy.roomsandlamps.entity.Room;
import com.shpakovskiy.roomsandlamps.service.CountryService;
import com.shpakovskiy.roomsandlamps.service.Tracker;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class IpSecurityService implements IpSecurityServiceInterface {

    private final Tracker tracker;
    private final CountryService countryService;

    public IpSecurityService(Tracker tracker, CountryService countryService) {
        this.tracker = tracker;
        this.countryService = countryService;
    }

    @Override
    public boolean checkEntrancePossibility(Room room, HttpServletRequest request, HttpServletResponse response) {
        String ip = request.getRemoteAddr();
        //System.out.println(ip);
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "134.17.139.29";
        }

        if (countryService.getCountryById(room.getCountryId())
                .getName().compareToIgnoreCase(tracker.getCountryByIp(ip)
                .getName()) == 0) {
            return true;
        }
        else {
            try {
                response.sendRedirect("/enter/access-denied");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        //return true;
    }
}
