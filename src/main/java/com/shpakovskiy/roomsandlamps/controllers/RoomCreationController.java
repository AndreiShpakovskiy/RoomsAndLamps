package com.shpakovskiy.roomsandlamps.controllers;

import com.shpakovskiy.roomsandlamps.entity.Room;
import com.shpakovskiy.roomsandlamps.service.CountryService;
import com.shpakovskiy.roomsandlamps.service.RoomsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class RoomCreationController {

    private final CountryService countryService;
    private final RoomsService roomsService;
    private Map<Integer, String> countries;

    public RoomCreationController(CountryService countryService, RoomsService roomsService) {
        this.countryService = countryService;
        this.roomsService = roomsService;
    }

    @RequestMapping("/new")
    private ModelAndView newRoom() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-room");
        countries = countryService.getAllCountriesAlphabetically();

        modelAndView.addObject("allRooms", countries);
        modelAndView.addObject("room", new Room());
        return modelAndView;
    }

    @RequestMapping(value="/create-room")
    private void createRoom(@ModelAttribute Room room, HttpServletResponse response) {
        room.setCountryId(countryService.getCountryByName(countries.get(room.getCountryId())).getId());//FIXME==========
        roomsService.addRoom(room);
        try {
            response.sendRedirect("/all");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
