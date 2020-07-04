package com.shpakovskiy.roomsandlamps.controllers;

import com.shpakovskiy.roomsandlamps.service.CountryService;
import com.shpakovskiy.roomsandlamps.service.RoomsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AllRoomsController {

    private final RoomsService roomsService;
    private final CountryService countryService;

    public AllRoomsController(RoomsService roomsService, CountryService countryService) {
        this.roomsService = roomsService;
        this.countryService = countryService;
    }

    @RequestMapping("/a")
    private ModelAndView basicDirection(/*HttpServletResponse response*/) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all-rooms");
        modelAndView.addObject("allRooms", roomsService.getAllRooms());
        modelAndView.addObject("countryService", countryService);
        return modelAndView;
    }

    @RequestMapping("/all")
    private ModelAndView showAllRooms() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all-rooms");
        modelAndView.addObject("allRooms", roomsService.getAllRooms());
        modelAndView.addObject("countryService", countryService);
        return modelAndView;
    }
}